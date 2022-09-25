package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.AircraftRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircraftRepositoryImpl implements AircraftRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
//    FlightRepository flightRepository = new FlightRepositoryImpl();
//    PassengerRepository passengerRepository = new PassengerRepositoryImpl();
//    GateRepository gateRepository = new GateRepositoryImpl();

    @Override
    public void create(Aircraft aircraft) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        System.out.println(" CREATE aircraft");
        Connection connection = CONNECTION_POOL.getConnection();
        try { //insert into aircrafts(aircraft_id, name) values (6, 'Denis');
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into aircrafts( number, model, aircarrier_id) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, aircraft.getNumber());
            preparedStatement.setString(2, aircraft.getModel());
            preparedStatement.setLong(3, aircraft.getAircarrierId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                aircraft.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    private static Aircraft findById(Long id, List<Aircraft> aircrafts) {
        return aircrafts.stream()
                .filter(aircraft -> aircraft.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Aircraft newAircraft = new Aircraft();
                    newAircraft.setId(id);
                    aircrafts.add(newAircraft);
                    return newAircraft;
                });
    }


    public List<Aircraft> map(ResultSet resultSet) throws SQLException {
        List<Aircraft> aircrafts = new ArrayList<>();
        AircraftRepository aircraftRepository = new AircraftRepositoryImpl();

        while (resultSet.next()) {
            aircrafts = mapRow(resultSet, aircrafts);
        }
        return aircrafts;
    }

    public List<Aircraft> mapRow(ResultSet resultSet, List<Aircraft> aircrafts) throws SQLException {
        long id = resultSet.getLong("aircraft_id");

        if (id != 0) {
            if (aircrafts == null) {
                aircrafts = new ArrayList<>();
            }
            Aircraft aircraft = findById(id, aircrafts);
            aircraft.setId(resultSet.getLong("aircraft_id"));
            aircraft.setNumber(resultSet.getInt("aircraft_number"));
            aircraft.setModel(resultSet.getString("model"));
            aircraft.setAircarrierId(resultSet.getLong("aircarrier_id"));
        }
        return aircrafts;
    }
//    aircrafts = map(resultSet);

    @Override
    public List<Aircraft> readAll() {
        System.out.println("READ all aircrafts");
        Connection connection = CONNECTION_POOL.getConnection();
        AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
        List<Aircraft> aircrafts = new ArrayList<>();
        Aircraft aircraft;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as aircraft_id, number as aircraft_number, aircarrier_id as aircarrier_id, model as model FROM airport.aircrafts;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            aircrafts = map(resultSet);

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
                    "SELECT id as aircraft_id, number as aircraft_number, aircarrier_id as aircarrier_id, model as model, seats as seats, service_date as service_date  " +
                            "FROM airport.aircrafts where aircrafts.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                aircraft = new Aircraft();
                aircraft.setId(resultSet.getLong("aircraft_id"));
                aircraft.setNumber(resultSet.getInt("aircraft_number"));
                aircraft.setModel(resultSet.getString("model"));
//                aircraft.setSeats(resultSet.getInt("seats"));
//                aircraft.setServiceDate(resultSet.getTimestamp("service_date").toLocalDateTime().toLocalDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return aircraft;
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
}