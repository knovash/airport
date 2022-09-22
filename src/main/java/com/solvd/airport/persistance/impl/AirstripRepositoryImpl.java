package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.AirstripRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirstripRepositoryImpl implements AirstripRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
//    FlightRepository flightRepository = new FlightRepositoryImpl();
//    PassengerRepository passengerRepository = new PassengerRepositoryImpl();
//    AirstripRepository airstripRepository = new AirstripRepositoryImpl();

//    private Long id;
//    private Integer number;

    @Override
    public void create(Airstrip airstrip) { // вызывается из сервиса. делает инсерт данных объекта в бд.
//        System.out.println("\nCREATE airstrip");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try { //insert into airstrips(airstrip_id, name) values (6, 'Denis');
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into airstrips(airstrip_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, airstrip.getAirstrip().getId());
//            preparedStatement.setString(2, airstrip.getName());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            while (resultSet.next()) {
//                airstrip.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<Airstrip> readAll() {
        System.out.println("READ all airstrips");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Airstrip> airstrips = new ArrayList<>();
        Airstrip airstrip;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as airstrip_id, number as number  FROM airport.airstrips;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                private Long id;
//                private Integer number;
                airstrip = new Airstrip();
                airstrip.setId(resultSet.getLong("airstrip_id"));
                airstrip.setNumber(resultSet.getInt("number"));
                airstrips.add(airstrip);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return airstrips;
    }

    @Override
    public Airstrip readById(Long id) {
        System.out.println("READ airstrip by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Airstrip airstrip = new Airstrip();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as airstrip_id, number as number  FROM airport.airstrips where airstrips.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                airstrip = new Airstrip();
                airstrip.setId(resultSet.getLong("airstrip_id"));
                airstrip.setNumber(resultSet.getInt("airstrip_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return airstrip;
    }

    @Override
    public List<Airstrip> readByAirportId(Long airportId) {
        System.out.println("READ all airstrips");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Airstrip> airstrips = new ArrayList<>();
        Airstrip airstrip;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as airstrip_id, number as number  FROM airport.airstrips where airstrips.airport_id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, airportId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                private Long id;
//                private Integer number;
                airstrip = new Airstrip();
                airstrip.setId(resultSet.getLong("airstrip_id"));
                airstrip.setNumber(resultSet.getInt("number"));
                airstrips.add(airstrip);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return airstrips;
    }

    @Override
    public void update(Airstrip airstrip) {
//        System.out.println("UPDATE airstrip");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update airstrips set name = ?, set airstrip_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, airstrip.getName());
//            preparedStatement.setLong(2, airstrip.getAirstrip().getId());
//            preparedStatement.setLong(3, airstrip.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
//        System.out.println("DELETE airstrip by id=" + id);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from airstrips where id = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteByNumber(Integer number) {
//        System.out.println("DELETE airstrip by number=" + number);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from airstrips where number = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, number);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}