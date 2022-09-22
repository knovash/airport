package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.ConnectionPool;
import com.solvd.airport.persistance.PassengerRepository;
import com.solvd.airport.persistance.PassportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepositoryImpl implements PassengerRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Passenger passenger) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        System.out.println("\nCREATE passenger");
        Connection connection = CONNECTION_POOL.getConnection();
        try { //insert into passengers(passport_id, name) values (6, 'Denis');
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into passengers(passport_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, passenger.getPassport().getId());
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passenger.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<Passenger> readAll() {
        System.out.println("READ all passengers");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as id, name as name, passport_id as passport_id from passengers order by id;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passenger = new Passenger();
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long passport_id = resultSet.getLong("passport_id");
                PassportRepository passportRepository = new PassportRepositoryImpl();
                Passport passport = passportRepository.readById(passport_id);
                passenger.setId(id);
                passenger.setName(name);
                passenger.setPassport(passport);
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return passengers;
    }

    @Override
    public Passenger readById(Long id) {
        System.out.println("READ passenger by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Passenger passenger = new Passenger();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as id, name as name, passport_id as passport_id from passengers where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Long passport_id = resultSet.getLong("passport_id");
                PassportRepository passportRepository = new PassportRepositoryImpl();
                Passport passport = passportRepository.readById(passport_id);
                passenger.setId(id);
                passenger.setName(name);
                passenger.setPassport(passport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return passenger;
    }

    @Override
    public void update(Passenger passenger) {
        System.out.println("UPDATE passenger");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update passengers set name = ?, set passport_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setLong(2, passenger.getPassport().getId());
            preparedStatement.setLong(3, passenger.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE passenger by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from passengers where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteByNumber(Integer number) {
        System.out.println("DELETE passenger by number=" + number);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from passengers where number = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }
}