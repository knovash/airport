package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PassportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassportRepositoryImpl implements PassportRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    protected static final String passportFields =
            "pt.id as passport_id," +
                    "pt.number as passport_number ";

    protected static final String passportReadAll =
            "select " +
                    passportFields +
                    "from passports pt ";

    @Override
    public void create(Passport passport) {
        System.out.println("REPOSITORY CREATE passport");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into passports(number) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, passport.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            passport.setId(resultSet.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Passport> readAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Passport> passports;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(passportReadAll, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            passports = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return passports;
    }

    @Override
    public Optional<Passport> readById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Passport passport = new Passport();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    passportReadAll + " where pt.id=?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer number = resultSet.getInt("passport_number");
                passport.setId(id);
                passport.setNumber(number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(passport);
    }

    @Override
    public void update(Passport passport) {
        System.out.println("UPDATE passport");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update passports set number = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, passport.getNumber());
            preparedStatement.setLong(2, passport.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE passport by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from passports where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    public List<Passport> map(ResultSet resultSet) throws SQLException {
        List<Passport> passports = new ArrayList<>();
        while (resultSet.next()) {
            passports = mapRow(resultSet, passports);
        }
        return passports;
    }

    public static List<Passport> mapRow(ResultSet resultSet, List<Passport> passports) throws SQLException {
        long id = resultSet.getLong("passport_id");
        if (id != 0) {
            if (passports == null) {
                passports = new ArrayList<>();
            }
            Passport passport = findById(id, passports);
            passport.setId(resultSet.getLong("passport_id"));
            passport.setNumber(resultSet.getInt("passport_number"));
        }
        return passports;
    }

    private static Passport findById(Long id, List<Passport> passports) {
        return passports.stream()
                .filter(passport -> passport.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Passport newPassport = new Passport();
                    newPassport.setId(id);
                    passports.add(newPassport);
                    return newPassport;
                });
    }
}
