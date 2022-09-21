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
        ystem.out.println("\nCREATE passport");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into passports(number) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, passport.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passport.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Passport> read() {
        System.out.println("\nREAD passport");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Passport> passports = new ArrayList<>();
        Passport passport;
        try {
            PreparedStatement statement = connection.prepareStatement("select id as id, number as number from passports order by id;", Statement.RETURN_GENERATED_KEYS);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                passport = new Passport();
                long id = resultSet.getLong("id");
                Integer number = resultSet.getInt("number");
                passport.setId(id);
                passport.setNumber(number);
                passports.add(passport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passports;
    }

    @Override
    public void update(Passport passport) {
        System.out.println("\nUPDATE passport");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update passports set number = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, passport.getNumber());
            preparedStatement.setLong(2, passport.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("\nDELETE passport by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from passports where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByNumber(Integer number) {
        System.out.println("\nDELETE passport by number=" + number);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from passports where number = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}