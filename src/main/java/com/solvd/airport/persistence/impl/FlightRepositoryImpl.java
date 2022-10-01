package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistence.*;
import com.solvd.airport.persistence.FlightRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
    private static final AirstripRepository airstripRepository = new AirstripRepositoryImpl();
    private static final DirectionRepository directionRepository = new DirectionRepositoryImpl();

    @Override
    public void create(Flight flight, Long aircarrierId) {
        System.out.println(" CREATE flight");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into flights(" +
                            "number, " +
                            "date, " +
                            "aircarrier_id, " +
                            "aircraft_id, " +
                            "airstrip_id" +
                            "direction_id" +
                            "pilot_id" +
                            ") values (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, flight.getNumber());
            preparedStatement.setDate(2, flight.getDate());
            preparedStatement.setLong(3, aircarrierId);
            preparedStatement.setLong(4, flight.getAircraft().getId());
            preparedStatement.setLong(5, flight.getAirstrip().getId());
            preparedStatement.setLong(6, flight.getDirection().getId());
            preparedStatement.setLong(7, flight.getPilot().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                flight.setId(resultSet.getLong(1)); 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private static Flight findById(Long id, List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Flight newFlight = new Flight();
                    newFlight.setId(id);
                    flights.add(newFlight);
                    return newFlight;
                });
    }

    public List<Flight> map(ResultSet resultSet) throws SQLException {
        List<Flight> flights = new ArrayList<>();
        while (resultSet.next()) {
            flights = mapRow(resultSet, flights);
        }
        return flights;
    }

    public static List<Flight> mapRow(ResultSet resultSet, List<Flight> flights) throws SQLException {
        long id = resultSet.getLong("flight_id");
        if (id != 0) {
            if (flights == null) flights = new ArrayList<>();
            Flight flight = findById(id, flights);

            flight.setId(resultSet.getLong("flight_id"));
            flight.setNumber(resultSet.getInt("flight_number"));
            flight.setDate(resultSet.getDate("flight_date"));

            List<Aircraft> aircrafts = AircraftRepositoryImpl.mapRow(resultSet, new ArrayList<>());
            flight.setAircraft(aircrafts.get(0));

            List<Airstrip> airstrips = AirstripRepositoryImpl.mapRow(resultSet, new ArrayList<>());
            flight.setAirstrip(airstrips.get(0));

            List<Direction> directions = DirectionRepositoryImpl.mapRow(resultSet, new ArrayList<>());
            flight.setDirection(directions.get(0));

            List<Pilot> pilots = PilotRepositoryImpl.mapRow(resultSet, new ArrayList<>());
            flight.setPilot(pilots.get(0));
        }
        return flights;
    }

    @Override
    public List<Flight> readAll() {
        System.out.println("READ all flights");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Flight> flights;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select \n" +
                            " flights.id as flight_id, \n" +
                            " flights.number as flight_number, \n" +
                            " flights.date as flight_date,  \n" +
                            " flights.aircarrier_id as aircarrier_id, \n" +
                            " flights.aircraft_id as aircraft_id, \n" +
                            " flights.airstrip_id as airstrip_id, \n" +
                            " flights.direction_id as direction_id, \n" +
                            " flights.pilot_id as pilot_id, \n" +
                            " aircarriers.id as aircarrier_id, \n" +
                            " aircarriers.name as aircarrier_name, \n" +
                            " aircrafts.id as aircraft_id, \n" +
                            " aircrafts.number as aircraft_number, \n" +
                            " aircrafts.model as model, \n" +
                            " airstrips.id as airstrip_id, \n" +
                            " airstrips.number as airstrip_number, \n" +
                            " directions.id as direction_id, \n" +
                            " directions.country as country, \n" +
                            " directions.distance as distance, \n" +
                            " gates.id as gate_id, \n" +
                            " gates.number as gate_number,\n" +
                            " gates.airport_id as airport_id,\n" +
                            " passengers.id as passenger_id, \n" +
                            " passengers.name as passenger_name, \n" +
                            " passengers.passport_id as passport_id, \n" +
                            " passports.number as passport_number,\n" +
                            " pilots.id as pilot_id, \n" +
                            " pilots.name as pilot_name, \n" +
                            " tickets.id as ticket_id, \n" +
                            " tickets.price as price, \n" +
                            " tickets.seat as seat\n" +
                            " from flights \n" +
                            " join aircarriers on flights.aircarrier_id = aircarriers.id \n" +
                            " join aircrafts on flights.aircraft_id = aircrafts.id \n" +
                            " join airstrips on flights.airstrip_id = airstrips.id \n" +
                            " join directions on flights.direction_id = directions.id \n" +
                            " join tickets on flights.id = tickets.flight_id \n" +
                            " join gates on gates.id = tickets.gate_id\n" +
                            " join passengers on passengers.id = tickets.passenger_id\n" +
                            " join passports on passports.id = passengers.passport_id\n" +
                            " join pilots on flights.pilot_id = pilots.id;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            flights = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return flights;
    }

    @Override
    public Flight readById(Long id) {
        System.out.println("READ flight by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Flight flight = new Flight();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select  " +
                            " flights.id as flight_id,  " +
                            " flights.aircarrier_id as carrier_id, " +
                            " flights.aircraft_id as aircraft_id, " +
                            " flights.airstrip_id as airstrip_id, " +
                            " flights.direction_id as direction_id, " +
                            " flights.number as number, " +
                            " flights.date as date  " +
                            " from flights where flights.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flight = new Flight();
                flight.setId(resultSet.getLong("flight_id"));
                flight.setAircraft(aircraftRepository.readById(resultSet.getLong("aircraft_id")));
                flight.setAirstrip(airstripRepository.readById(resultSet.getLong("airstrip_id")));
                flight.setDirection(directionRepository.readById(resultSet.getLong("direction_id")));
                flight.setNumber(resultSet.getInt("number"));
                flight.setDate(resultSet.getDate("date"));
//                flight.setTickets(ticketRepository.readByFlightId(resultSet.getLong("flight_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return flight;
    }

    @Override
    public void update(Flight flight) {
//        System.out.println("UPDATE flight");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update flights set name = ?, set flight_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, flight.getName());
//            preparedStatement.setLong(2, flight.getFlight().getId());
//            preparedStatement.setLong(3, flight.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
//        System.out.println("DELETE flight by id=" + id);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from flights where id = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}
