package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PilotRepository;
import com.solvd.airport.persistence.PassportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.airport.persistence.impl.PassportRepositoryImpl.passportFields;

public class PilotRepositoryImpl implements PilotRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final PassportRepository passportRepository = new PassportRepositoryImpl();

    protected static final String pilotFields =
            "pl.id as pilot_id, " +
                    "pl.name as pilot_name ";

    protected static final String pilotReadAll =
            "select " +
                    pilotFields +
                    "from pilots pl ";

    @Override
    public void create(Pilot pilot, Long aircarrierId) {
        System.out.println("REPOSITORY CREATE pilot");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into pilots(name, passport_id) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pilot.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            pilot.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Pilot> readById(Long id) {
        System.out.println("REPOSITORY READ pilot by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Pilot pilot = new Pilot();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    pilotReadAll +
                            " where pl.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            pilot = map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(pilot);
    }

    @Override
    public List<Pilot> readAll() {
        System.out.println("REPOSITORY READ all pilots");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Pilot> pilots;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    pilotReadAll, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            pilots = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return pilots;
    }

    @Override
    public void update(Pilot pilot, Long aircarrierId) {
        System.out.println("UPDATE pilot");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update pilots set name = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pilot.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE pilot by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from pilots where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    public List<Pilot> map(ResultSet resultSet) throws SQLException {
        List<Pilot> pilots = new ArrayList<>();
        while (resultSet.next()) {
            pilots = mapRow(resultSet, pilots);
        }
        return pilots;
    }

    public static List<Pilot> mapRow(ResultSet resultSet, List<Pilot> pilots) throws SQLException {
        long id = resultSet.getLong("pilot_id");
        if (id != 0) {
            if (pilots == null) {
                pilots = new ArrayList<>();
            }
            Pilot pilot = findById(id, pilots);
            pilot.setId(resultSet.getLong("pilot_id"));
            pilot.setName(resultSet.getString("pilot_name"));
        }
        return pilots;
    }

    private static Pilot findById(Long id, List<Pilot> pilots) {
        return pilots.stream()
                .filter(pilot -> pilot.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Pilot newPilot = new Pilot();
                    newPilot.setId(id);
                    pilots.add(newPilot);
                    return newPilot;
                });
    }
}
