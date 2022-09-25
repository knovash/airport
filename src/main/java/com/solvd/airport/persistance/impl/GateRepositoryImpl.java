package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.port.Gate;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.GateRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GateRepositoryImpl implements GateRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
//    FlightRepository flightRepository = new FlightRepositoryImpl();
//    PassengerRepository passengerRepository = new PassengerRepositoryImpl();
//    GateRepository gateRepository = new GateRepositoryImpl();

    @Override
    public void create(Gate gate) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        System.out.println(" CREATE gate");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into gates(number, airport_id) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, gate.getNumber());
            preparedStatement.setLong(1, gate.getAirportId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                gate.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }


    public Gate map(ResultSet resultSet) throws SQLException {
        Gate gate = new Gate();
        gate.setId(resultSet.getLong("gate_id"));
        gate.setNumber(resultSet.getInt("gate_number"));
        gate.setAirportId(resultSet.getLong("gate_airport_id"));
        return gate;
    }
    
    @Override
    public List<Gate> readAll() {
        System.out.println("READ all gates");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Gate> gates = new ArrayList<>();
        Gate gate;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as gate_id, number as gate_number, airport_id as gate_airport_id FROM airport.gates;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                gates.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return gates;
    }

    @Override
    public Gate readById(Long id) {
        System.out.println("READ gate by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Gate gate = new Gate();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as gate_id, number as number  FROM airport.gates where gates.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                gate = new Gate();
                gate.setId(resultSet.getLong("gate_id"));
                gate.setNumber(resultSet.getInt("gate_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return gate;
    }

    @Override
    public void update(Gate gate) {
//        System.out.println("UPDATE gate");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update gates set name = ?, set gate_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, gate.getName());
//            preparedStatement.setLong(2, gate.getGate().getId());
//            preparedStatement.setLong(3, gate.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
//        System.out.println("DELETE gate by id=" + id);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from gates where id = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}