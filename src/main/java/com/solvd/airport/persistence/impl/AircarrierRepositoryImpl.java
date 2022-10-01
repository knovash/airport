package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.persistence.*;
import com.solvd.airport.persistence.AircarrierRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircarrierRepositoryImpl implements AircarrierRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Aircarrier aircarrier) {
        System.out.println(" CREATE aircarrier name=" + aircarrier.getName());
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into aircarriers(name) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aircarrier.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                aircarrier.setId(resultSet.getLong(1));
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

    public static List<Aircarrier> mapRow(ResultSet resultSet, List<Aircarrier> aircarriers) throws SQLException {
        long id = resultSet.getLong("aircarrier_id");
        if (id != 0) {
            if (aircarriers == null) { aircarriers = new ArrayList<>();}
            Aircarrier aircarrier = findById(id, aircarriers);

            aircarrier.setId(resultSet.getLong("aircarrier_id"));
            aircarrier.setName(resultSet.getString("aircarrier_name"));

            List<Pilot> pilots = PilotRepositoryImpl.mapRow(resultSet, aircarrier.getPilots());
            aircarrier.setPilots(pilots);

            List<Aircraft> aircrafts = AircraftRepositoryImpl.mapRow(resultSet, aircarrier.getAircrafts());
            aircarrier.setAircrafts(aircrafts);

            List<Flight> flights = FlightRepositoryImpl.mapRow(resultSet, aircarrier.getFlights());
            aircarrier.setFlights(flights);
        }
        return aircarriers;
    }

    @Override
    public List<Aircarrier> readAll() {
        System.out.println("READ all aircarriers");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Aircarrier> aircarriers;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select\n" +
                            "aircarriers.id as aircarrier_id, \n" +
                            "aircarriers.name as aircarrier_name, \n" +
                            "tickets.id as ticket_id, \n" +
                            "tickets.price as price, \n" +
                            "tickets.seat as seat,\n" +
                            "aircrafts.id as aircraft_id, \n" +
                            "aircrafts.number as aircraft_number, \n" +
                            "aircrafts.model as model, \n" +
                            "airstrips.id as airstrip_id, \n" +
                            "airstrips.number as airstrip_number, \n" +
                            "directions.id as direction_id, \n" +
                            "directions.country as country, \n" +
                            "directions.distance as distance,\n" +
                            "flights.id as flight_id, \n" +
                            "flights.number as flight_number, \n" +
                            "flights.date as flight_date,  \n" +
                            "flights.aircarrier_id as aircarrier_id, \n" +
                            "flights.aircraft_id as aircraft_id, \n" +
                            "flights.airstrip_id as airstrip_id, \n" +
                            "flights.direction_id as direction_id, \n" +
                            "flights.pilot_id as pilot_id,\n" +
                            "gates.id as gate_id, \n" +
                            "gates.number as gate_number,\n" +
                            "gates.airport_id as airport_id,\n" +
                            "passengers.id as passenger_id, \n" +
                            "passengers.name as passenger_name, \n" +
                            "passengers.passport_id as passport_id, \n" +
                            "passports.number as passport_number,\n" +
                            "pilots.id as pilot_id, \n" +
                            "pilots.name as pilot_name\n" +
                            "FROM aircarriers \n" +
                            "left join pilots on aircarriers.id = pilots.aircarrier_id \n" +
                            "left join aircrafts on aircarriers.id = aircrafts.aircarrier_id \n" +
                            "left join flights on aircarriers.id = flights.aircarrier_id \n" +
                            "left join tickets on tickets.flight_id = flights.id\n" +
                            "left join passengers on passengers.id = tickets.passenger_id\n" +
                            "left join passports on passports.id = passengers.passport_id\n" +
                            "left join gates on gates.id = tickets.gate_id \n" +
                            "left join airstrips on flights.airstrip_id = airstrips.id \n" +
                            "left join directions on flights.direction_id = directions.id \n" +
                            "order by aircarriers.id;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            aircarriers = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return aircarriers;
    }

    @Override
    public Aircarrier readById(Long id) {
        System.out.println("READ aircarriers by ID=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        List<Aircarrier> aircarriers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT \n" +
                            " aircarriers.id as aircarrier_id, \n" +
                            " aircarriers.name as aircarrier_name, \n" +
                            " pilots.id as pilot_id, \n" +
                            " pilots.name as pilot_name, \n" +
                            " pilots.aircarrier_id as pilot_aircarrier_id, \n" +
                            " aircrafts.id as aircraft_id, \n" +
                            " aircrafts.number as aircraft_number, \n" +
                            " aircrafts.model as model, \n" +
                            " flights.number as flight_number, \n" +
                            " flights.id as flight_id, \n" +
                            " flights.date as flight_date \n" +
                            " FROM aircarriers \n" +
                            " left join pilots on aircarriers.id = pilots.aircarrier_id \n" +
                            " left join aircrafts on aircarriers.id = aircrafts.aircarrier_id \n" +
                            " left join flights on aircarriers.id = flights.aircarrier_id \n" +
                            " left join tickets on flights.id = tickets.flight_id \n" +
                            " where aircarriers.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            aircarriers = mapRow(resultSet, aircarriers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return aircarriers.get(0);
    }

    @Override
    public void update(Aircarrier aircarrier) {
        System.out.println("UPDATE aircarrier ID=" + aircarrier.getId());
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update aircarriers set name = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aircarrier.getName());
            preparedStatement.setLong(2, aircarrier.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE aircarrier by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from aircarriers where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }
}
