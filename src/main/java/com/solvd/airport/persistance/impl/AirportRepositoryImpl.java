package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.port.Airport;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.AirportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportRepositoryImpl implements AirportRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    
    @Override
    public void create(Airport airport) {
        System.out.println(" CREATE airport");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into airports(name) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, airport.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                airport.setId(resultSet.getLong(1)); 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        CONNECTION_POOL.releaseConnection(connection);}
    }

    private static Airport findById(Long id, List<Airport> airports) {
        return airports.stream()
                .filter(airport -> airport.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Airport newAirport = new Airport();
                    newAirport.setId(id);
                    airports.add(newAirport);
                    return newAirport;
                });
    }

    public List<Airport> map(ResultSet resultSet) throws SQLException {
        List<Airport> airports = new ArrayList<>();
        while (resultSet.next()) {
            airports = mapRow(resultSet, airports);
        }
        return airports;
    }

    public static List<Airport> mapRow(ResultSet resultSet, List<Airport> airports) throws SQLException {
        long id = resultSet.getLong("airport_id");
        if (id != 0) {
            if (airports == null) airports = new ArrayList<>();

            Airport airport = findById(id, airports);

            airport.setId(resultSet.getLong("airport_id"));
            airport.setName(resultSet.getString("name"));
        }
        return airports;
    }

    @Override
    public List<Airport> readAll() {
        System.out.println("READ all airports");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Airport> airports;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as airport_id, name as name  FROM airport.airports;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            airports = map(resultSet);

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
//                airport.setAirstrips(airstripRepository.readByAirportId(resultSet.getLong("airport_id")));
//                airport.setGates(gateRepository.readByAirportId(resultSet.getLong("airport_id")));
//                airport.setAircarriers(aircarrierRepository.readByAirportId(resultSet.getLong("airport_id")));
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
}