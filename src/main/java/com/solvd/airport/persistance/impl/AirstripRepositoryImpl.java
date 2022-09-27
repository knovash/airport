package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.AirstripRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirstripRepositoryImpl implements AirstripRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Airstrip airstrip, Long airportId) {
        System.out.println(" CREATE airstrip");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into airstrips(number, airport_id) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, airstrip.getNumber());
            preparedStatement.setLong(2, airstrip.getAirportId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                airstrip.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    private static Airstrip findById(Long id, List<Airstrip> airstrips) {
        return airstrips.stream()
                .filter(airstrip -> airstrip.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Airstrip newAirstrip = new Airstrip();
                    newAirstrip.setId(id);
                    airstrips.add(newAirstrip);
                    return newAirstrip;
                });
    }

    public List<Airstrip> map(ResultSet resultSet) throws SQLException {
        List<Airstrip> airstrips = new ArrayList<>();
        while (resultSet.next()) {
            airstrips = mapRow(resultSet, airstrips);
        }
        return airstrips;
    }

    public static List<Airstrip> mapRow(ResultSet resultSet, List<Airstrip> airstrips) throws SQLException {
        long id = resultSet.getLong("airstrip_id");
        if (id != 0) {
            if (airstrips == null) airstrips = new ArrayList<>();
            Airstrip airstrip = findById(id, airstrips);

            airstrip.setId(resultSet.getLong("airstrip_id"));
            airstrip.setNumber(resultSet.getInt("airstrip_number"));
            airstrip.setAirportId(resultSet.getLong("airport_id"));
        }
        return airstrips;
    }

    @Override
    public List<Airstrip> readAll() {
        System.out.println("READ all airstrips");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Airstrip> airstrips;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as airstrip_id, number as airstrip_number, airport_id as airport_id  FROM airport.airstrips;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            airstrips = map(resultSet);

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
}