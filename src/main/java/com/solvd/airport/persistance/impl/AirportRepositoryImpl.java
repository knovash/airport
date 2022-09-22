package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.port.Airport;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.domain.port.Gate;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.AirportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportRepositoryImpl implements AirportRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    AirstripRepository airstripRepository = new AirstripRepositoryImpl();
    AircarrierRepository aircarrierRepository = new AircarrierRepositoryImpl();
    GateRepository gateRepository = new GateRepositoryImpl();

//    private Long id;
//    private String name;
//    private List<Airstrip> airstrips;
//    private List<Gate> gates;
//    private List<Aircarrier> aircarriers;

    @Override
    public void create(Airport airport) { // вызывается из сервиса. делает инсерт данных объекта в бд.
//        System.out.println("\nCREATE airport");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try { //insert into airports(airport_id, name) values (6, 'Denis');
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into airports(airport_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, airport.getAirport().getId());
//            preparedStatement.setString(2, airport.getName());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            while (resultSet.next()) {
//                airport.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<Airport> readAll() {
        System.out.println("READ all airports");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Airport> airports = new ArrayList<>();
        Airport airport;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as airport_id, name as name  FROM airport.airports;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                private Long id;
//                private String name;
//                private List<Airstrip> airstrips;
//                private List<Gate> gates;
//                private List<Aircarrier> aircarriers;
                airport = new Airport();
                airport.setId(resultSet.getLong("airport_id"));
                airport.setName(resultSet.getString("name"));
                airport.setAirstrips(airstripRepository.readByAirportId(resultSet.getLong("airport_id")));
                airport.setGates(gateRepository.readByAirportId(resultSet.getLong("airport_id")));
                airport.setAircarriers(aircarrierRepository.readByAirportId(resultSet.getLong("airport_id")));
                airports.add(airport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return airports;
    }

    @Override
    public Airport readById(Long id) {
        System.out.println("READ airport by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Airport airport = new Airport();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as airport_id, name as name  FROM airport.airports where airports.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                airport = new Airport();
                airport.setId(resultSet.getLong("airport_id"));
                airport.setName(resultSet.getString("name"));
                airport.setAirstrips(airstripRepository.readByAirportId(resultSet.getLong("airport_id")));
                airport.setGates(gateRepository.readByAirportId(resultSet.getLong("airport_id")));
                airport.setAircarriers(aircarrierRepository.readByAirportId(resultSet.getLong("airport_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return airport;
    }

    @Override
    public void update(Airport airport) {
//        System.out.println("UPDATE airport");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update airports set name = ?, set airport_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, airport.getName());
//            preparedStatement.setLong(2, airport.getAirport().getId());
//            preparedStatement.setLong(3, airport.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
//        System.out.println("DELETE airport by id=" + id);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from airports where id = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteByNumber(Integer number) {
//        System.out.println("DELETE airport by number=" + number);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from airports where number = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, number);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}