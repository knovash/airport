package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.PilotRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotRepositoryImpl implements PilotRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Pilot pilot, Long aircarrier_id) { 
        System.out.println(" CREATE pilot");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into pilots(aircarrier_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, aircarrier_id);
            preparedStatement.setString(2, pilot.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                pilot.setId(resultSet.getLong(1)); 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
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
            if (pilots == null) pilots = new ArrayList<>();
            Pilot pilot = findById(id, pilots);

            pilot.setId(resultSet.getLong("pilot_id"));
            pilot.setName(resultSet.getString("pilot_name"));
            pilot.setAircarrierId(resultSet.getLong("aircarrier_id"));
        }
        return pilots;
    }

    @Override
    public List<Pilot> readAll() {
        System.out.println("READ all pilots");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Pilot> pilots;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select  pilots.id as pilot_id, pilots.name as pilot_name, aircarrier_id as aircarrier_id from pilots;", Statement.RETURN_GENERATED_KEYS);
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
    public Pilot readById(Long id) {
        System.out.println("READ pilot by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Pilot pilot = new Pilot();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select  pilots.id as pilot_id, pilots.name as name from pilots where pilots.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pilot = new Pilot();
                pilot.setId(resultSet.getLong("pilot_id"));
                pilot.setName(resultSet.getString("name"));
                Long pilotId = resultSet.getLong("pilot_id");
//                pilot.setFlights(flightRepository.readByPilotId(pilotId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return pilot;
    }

    @Override
    public void update(Pilot pilot) {
//        System.out.println("UPDATE pilot");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update pilots set name = ?, set pilot_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, pilot.getName());
//            preparedStatement.setLong(2, pilot.getPilot().getId());
//            preparedStatement.setLong(3, pilot.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
//        System.out.println("DELETE pilot by id=" + id);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from pilots where id = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}