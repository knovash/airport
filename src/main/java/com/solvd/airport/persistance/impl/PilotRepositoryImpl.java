package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.PilotRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotRepositoryImpl implements PilotRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    PilotLicenseRepository pilotLicenseRepository = new PilotLicenceRepositoryImpl();
    FlightRepository flightRepository = new FlightRepositoryImpl();

//    private Long id;
//    private String name;
//    private PilotLicense pilotLicense;
//    private List<Flight> Flights;

    @Override
    public void create(Pilot pilot) { // вызывается из сервиса. делает инсерт данных объекта в бд.
//        System.out.println("\nCREATE pilot");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try { //insert into pilots(pilot_id, name) values (6, 'Denis');
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into pilots(pilot_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, pilot.getPilot().getId());
//            preparedStatement.setString(2, pilot.getName());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            while (resultSet.next()) {
//                pilot.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<Pilot> readAll() {
        System.out.println("READ all pilots");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Pilot> pilots = new ArrayList<>();
        Pilot pilot;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select  pilots.id as pilot_id, pilots.name as name,  pilots.pilot_license_id as license_id from pilots;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                private Long id;
//                private String name;
//                private PilotLicense pilotLicense;
//                private List<Flight> Flights;
                pilot = new Pilot();
                pilot.setId(resultSet.getLong("pilot_id"));
                pilot.setName(resultSet.getString("name"));
                pilot.setPilotLicense(pilotLicenseRepository.readById(resultSet.getLong("license_id")));
                pilot.setFlights(flightRepository.readByPilotId(resultSet.getLong("pilot_id")));
                pilots.add(pilot);
            }
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
                    "select  pilots.id as pilot_id, pilots.name as name,  pilots.pilot_license_id as license_id from pilots where pilots.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                private Long id;
//                private String name;
//                private PilotLicense pilotLicense;
//                private List<Flight> Flights;
                pilot = new Pilot();
                pilot.setId(resultSet.getLong("pilot_id"));
                pilot.setName(resultSet.getString("name"));
                pilot.setPilotLicense(pilotLicenseRepository.readById(resultSet.getLong("license_id")));
                Long pilotId = resultSet.getLong("pilot_id");
                pilot.setFlights(flightRepository.readByPilotId(pilotId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return pilot;
    }


    @Override
    public List<Pilot> readByAircarrierId(Long aircarrierId) {
        System.out.println("READ all pilots");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Pilot> pilots = new ArrayList<>();
        Pilot pilot;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select  pilots.id as pilot_id, pilots.name as name,  pilots.pilot_license_id as license_id from pilots where pilots.aircarrier_id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, aircarrierId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                private Long id;
//                private String name;
//                private PilotLicense pilotLicense;
//                private List<Flight> Flights;
                pilot = new Pilot();
                pilot.setId(resultSet.getLong("pilot_id"));
                pilot.setName(resultSet.getString("name"));
                pilot.setPilotLicense(pilotLicenseRepository.readById(resultSet.getLong("license_id")));
                pilot.setFlights(flightRepository.readByPilotId(resultSet.getLong("pilot_id")));
                pilots.add(pilot);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return pilots;
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

    @Override
    public void deleteByNumber(Integer number) {
//        System.out.println("DELETE pilot by number=" + number);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from pilots where number = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, number);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}