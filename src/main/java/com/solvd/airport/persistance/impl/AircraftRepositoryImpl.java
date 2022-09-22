package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.AircraftRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircraftRepositoryImpl implements AircraftRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    FlightRepository flightRepository = new FlightRepositoryImpl();
    PassengerRepository passengerRepository = new PassengerRepositoryImpl();
    GateRepository gateRepository = new GateRepositoryImpl();

//    private Long id;
//    private Integer number;
//    private String model;
//    private Integer seats;
//    private LocalDate serviceDate;

    @Override
    public void create(Aircraft aircraft) { // вызывается из сервиса. делает инсерт данных объекта в бд.
//        System.out.println("\nCREATE aircraft");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try { //insert into aircrafts(aircraft_id, name) values (6, 'Denis');
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into aircrafts(aircraft_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, aircraft.getAircraft().getId());
//            preparedStatement.setString(2, aircraft.getName());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            while (resultSet.next()) {
//                aircraft.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<Aircraft> readAll() {
        System.out.println("READ all aircrafts");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Aircraft> aircrafts = new ArrayList<>();
        Aircraft aircraft;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as aircraft_id, number as number, aircarrier_id as aircarrier_id, model as model, seats as seats, service_date as service_date \n" +
                            "FROM airport.aircrafts;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//    private Long id;
//    private Integer number;
//    private String model;
//    private Integer seats;
//    private LocalDate serviceDate;
                aircraft = new Aircraft();
                aircraft.setId(resultSet.getLong("aircraft_id"));
                aircraft.setNumber(resultSet.getInt("number"));
                aircraft.setModel(resultSet.getString("model"));
                aircraft.setSeats(resultSet.getInt("seats"));
                aircraft.setServiceDate(resultSet.getTimestamp("service_date").toLocalDateTime().toLocalDate());
                aircrafts.add(aircraft);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return aircrafts;
    }

    @Override
    public Aircraft readById(Long id) {
        System.out.println("READ aircraft by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Aircraft aircraft = new Aircraft();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as aircraft_id, number as number, aircarrier_id as aircarrier_id, model as model, seats as seats, service_date as service_date \n" +
                            "FROM airport.aircrafts where aircrafts.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                aircraft = new Aircraft();
                aircraft.setId(resultSet.getLong("aircraft_id"));
                aircraft.setNumber(resultSet.getInt("number"));
                aircraft.setModel(resultSet.getString("model"));
                aircraft.setSeats(resultSet.getInt("seats"));
                aircraft.setServiceDate(resultSet.getTimestamp("service_date").toLocalDateTime().toLocalDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return aircraft;
    }

    @Override
    public List<Aircraft> readByAircarrierId(Long aircarrierId) {
        System.out.println("READ all aircrafts");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Aircraft> aircrafts = new ArrayList<>();
        Aircraft aircraft;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as aircraft_id, number as number, aircarrier_id as aircarrier_id, model as model, seats as seats, service_date as service_date \n" +
                            "FROM airport.aircrafts where aircrafts.aircarrier_id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, aircarrierId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//    private Long id;
//    private Integer number;
//    private String model;
//    private Integer seats;
//    private LocalDate serviceDate;
                aircraft = new Aircraft();
                aircraft.setId(resultSet.getLong("aircraft_id"));
                aircraft.setNumber(resultSet.getInt("number"));
                aircraft.setModel(resultSet.getString("model"));
                aircraft.setSeats(resultSet.getInt("seats"));
                aircraft.setServiceDate(resultSet.getTimestamp("service_date").toLocalDateTime().toLocalDate());
                aircrafts.add(aircraft);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return aircrafts;
    }

    @Override
    public void update(Aircraft aircraft) {
//        System.out.println("UPDATE aircraft");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update aircrafts set name = ?, set aircraft_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, aircraft.getName());
//            preparedStatement.setLong(2, aircraft.getAircraft().getId());
//            preparedStatement.setLong(3, aircraft.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
//        System.out.println("DELETE aircraft by id=" + id);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from aircrafts where id = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteByNumber(Integer number) {
//        System.out.println("DELETE aircraft by number=" + number);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from aircrafts where number = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, number);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}