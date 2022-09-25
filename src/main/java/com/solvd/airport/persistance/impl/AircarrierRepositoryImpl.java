package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.flight.Flight;
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
        CONNECTION_POOL.releaseConnection(connection);}
    }

    @Override
    public Aircarrier map(ResultSet resultSet) throws SQLException {
        Aircarrier aircarrier = new Aircarrier();

        List<Flight> flights = new ArrayList<>();
        Flight flight;
        flight = flightRepository.map(resultSet);
        flights.add(flight);

        List<Aircraft> aircrafts = new ArrayList<>();
        Aircraft aircraft;
        aircraft = aircraftRepository.map(resultSet);
        aircrafts.add(aircraft);

        List<Pilot> pilots = new ArrayList<>();
        Pilot pilot;
        pilot = pilotRepository.map(resultSet);
        pilots.add(pilot);

        aircarrier.setFlights(flights);
        aircarrier.setAircrafts(aircrafts);
        aircarrier.setPilots(pilots);
        aircarrier.setId(resultSet.getLong("aircarrier_id"));
        aircarrier.setName(resultSet.getString("name"));
        return aircarrier;
    }

    @Override
    public List<Aircarrier> readAll() {
        System.out.println("READ all aircarriers");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Aircarrier> aircarriers = new ArrayList<>();
        Aircarrier aircarrier;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id as aircarrier_id, name as name FROM airport.aircarriers;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                aircarriers.add(map(resultSet));
            }
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