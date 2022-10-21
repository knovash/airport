package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.AircraftRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AircraftRepositoryImpl implements AircraftRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    protected static final String aircraftFields =
            "af.id as aircraft_id, " +
                    "        af.number as aircraft_number, " +
                    "        af.model as aircraft_model ";

    protected static final String aircraftReadAll =
            "select " +
                    aircraftFields +
                    "        FROM aircrafts af";

    @Override
    public void create(Aircraft aircraft, Long aircarrierId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into aircrafts(mode, number) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aircraft.getModel());
            preparedStatement.setInt(2, aircraft.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            aircraft.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Aircraft> readById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Aircraft aircraft;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    aircraftReadAll +
                            " where af.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            aircraft = map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(aircraft);
    }

    @Override
    public List<Aircraft> readAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Aircraft> aircrafts;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    aircraftReadAll, Statement.RETURN_GENERATED_KEYS);
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
    public void update(Aircraft aircraft, Long aircarrierId) {
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from aircrafts where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    public List<Aircraft> map(ResultSet resultSet) throws SQLException {
        List<Aircraft> aircrafts = new ArrayList<>();
        while (resultSet.next()) {
            aircrafts = mapRow(resultSet, aircrafts);
        }
        return aircrafts;
    }

    public static List<Aircraft> mapRow(ResultSet resultSet, List<Aircraft> aircrafts) throws SQLException {
        long id = resultSet.getLong("aircraft_id");
        if (id != 0) {
            if (aircrafts == null) {
                aircrafts = new ArrayList<>();
            }
            Aircraft aircraft = findById(id, aircrafts);
            aircraft.setId(resultSet.getLong("aircraft_id"));
            aircraft.setModel(resultSet.getString("aircraft_model"));
            aircraft.setNumber(resultSet.getInt("aircraft_number"));
        }
        return aircrafts;
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
}
