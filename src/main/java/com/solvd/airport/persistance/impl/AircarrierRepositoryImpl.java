package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.AircarrierRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircarrierRepositoryImpl implements AircarrierRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final FlightRepository flightRepository = new FlightRepositoryImpl();
    private static final AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
    private static final PilotRepository pilotRepository = new PilotRepositoryImpl();

    @Override
    public void create(Aircarrier aircarrier) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        System.out.println(" CREATE aircarrier");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into aircarriers(name) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aircarrier.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                aircarrier.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private static Aircarrier findById(Long id, List<Aircarrier> aircarriers) {
        return aircarriers.stream()
                .filter(aircarrier -> aircarrier.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Aircarrier newAircarrier = new Aircarrier();
                    newAircarrier.setId(id);
                    aircarriers.add(newAircarrier);
                    return newAircarrier;
                });
    }


    public List<Aircarrier> map(ResultSet resultSet) throws SQLException {
        List<Aircarrier> aircarriers = new ArrayList<>();
        while (resultSet.next()) {
            aircarriers = mapRow(resultSet, aircarriers);
        }
        return aircarriers;
    }


    public List<Aircarrier> mapRow(ResultSet resultSet, List<Aircarrier> aircarriers) throws SQLException {
        long id = resultSet.getLong("aircarrier_id");

        if (id != 0) {
            if (aircarriers == null) {
                aircarriers = new ArrayList<>();
            }
            Aircarrier aircarrier = findById(id, aircarriers);
            aircarrier.setName(resultSet.getString("aircarrier_name"));
            aircarrier.setId(resultSet.getLong("aircarrier_id"));

        }
        return aircarriers;
    }


//    aircarriers = map(resultSet);

//     aircarrier.setName(resultSet.getString("aircarrier_name"));
//             aircarrier.setId(resultSet.getLong("aircarrier_id"));

//    @Override
//    public List<Aircarrier> map(ResultSet resultSet) throws SQLException {
////        Aircarrier aircarrier = new Aircarrier();
//        List<Aircarrier> aircarriers = new ArrayList<>();
//
//        while (resultSet.next()) {
//            Long id = resultSet.getLong("aircarrier_id");
//
//            Aircarrier aircarrier = findById(id, aircarriers);
//
//            aircarrier.setName(resultSet.getString("aircarrier_name"));
//            aircarrier.setId(resultSet.getLong("aircarrier_id"));
//
//            List<Pilot> pilots;
//            pilots = pilotRepository.mapRow(resultSet);
//            aircarrier.setPilots(pilots);
//        }
////        return aircarriers;
//    }
//


    @Override
    public List<Aircarrier> readAll() {
        System.out.println("READ all aircarriers");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Aircarrier> aircarriers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT \n" +
                            "aircarriers.id as aircarrier_id, \n" +
                            "aircarriers.name as aircarrier_name,\n" +
                            "pilots.id as pilot_id,\n" +
                            "pilots.name as pilot_name,\n" +
                            "pilots.aircarrier_id as pilot_aircarrier_id\n" +
                            "FROM aircarriers\n" +
                            "join pilots on aircarriers.id = pilots.aircarrier_id;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            aircarriers = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return aircarriers;
    }


    @Override
    public Aircarrier readById(Long id) {
        System.out.println("READ aircarrier by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Aircarrier aircarrier = new Aircarrier();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as aircarrier_id, name as name FROM airport.aircarriers where aircarriers.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                aircarrier = new Aircarrier();
                aircarrier.setId(resultSet.getLong("aircarrier_id"));
                aircarrier.setName(resultSet.getString("name"));
//                aircarrier.setFlights(flightRepository.readByAircarrierId(resultSet.getLong("aircarrier_id")));
//                aircarrier.setAircrafts(aircraftRepository.readByAircarrierId(resultSet.getLong("aircarrier_id")));
//                aircarrier.setPilots(pilotRepository.readByAircarrierId(resultSet.getLong("aircarrier_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return aircarrier;
    }

    @Override
    public void update(Aircarrier aircarrier) {
//        System.out.println("UPDATE aircarrier");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update aircarriers set name = ?, set aircarrier_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, aircarrier.getName());
//            preparedStatement.setLong(2, aircarrier.getAircarrier().getId());
//            preparedStatement.setLong(3, aircarrier.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
//        System.out.println("DELETE aircarrier by id=" + id);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from aircarriers where id = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}