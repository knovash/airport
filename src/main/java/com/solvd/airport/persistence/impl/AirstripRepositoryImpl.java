package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistence.AirstripRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirstripRepositoryImpl implements AirstripRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    protected static final String airstripFields =
            "str.id as airstrip_id, " +
                    "        str.number as airstrip_number ";

    protected static final String airstripReadAll =
            "select " +
                    airstripFields +
                    "        FROM airstrips str";

    @Override
    public void create(Airstrip airstrip, Long airportId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into airstrips(mode, number) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, airstrip.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            airstrip.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Airstrip> readById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Airstrip airstrip;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    airstripReadAll +
                            " where str.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            airstrip = map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(airstrip);
    }

    @Override
    public List<Airstrip> readAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Airstrip> airstrips;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    airstripReadAll, Statement.RETURN_GENERATED_KEYS);
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
    public void update(Airstrip airstrip, Long airportId) {
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from airstrips where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
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
            if (airstrips == null) {
                airstrips = new ArrayList<>();
            }
            Airstrip airstrip = findById(id, airstrips);
            airstrip.setId(resultSet.getLong("airstrip_id"));
            airstrip.setNumber(resultSet.getInt("airstrip_number"));
        }
        return airstrips;
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
}
