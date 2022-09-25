package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.ConnectionPool;
import com.solvd.airport.persistance.PassportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassportRepositoryImpl implements PassportRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Passport passport) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        System.out.println("CREATE passport");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into passports(number) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, passport.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passport.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        CONNECTION_POOL.releaseConnection(connection);}
    }

    @Override
    public Passport map(ResultSet resultSet) throws SQLException {
        Passport passport = new Passport();
        passport.setId(resultSet.getLong("passport_id"));
        passport.setNumber(resultSet.getInt("passport_number"));
        return passport;
    }

    @Override
    public List<Passport> readAll() {
        System.out.println("READ all passports");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Passport> passports = new ArrayList<>();
        Passport passport;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as passport_id, number as passport_number from passports;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passports.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return passports;
    }

    @Override
    public Passport readById(Long id) {
        System.out.println("READ passport by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Passport passport = new Passport();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as id, number as number from passports where passports.id=?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer number = resultSet.getInt("number");
                passport.setId(id);
                passport.setNumber(number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return passport;
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
}