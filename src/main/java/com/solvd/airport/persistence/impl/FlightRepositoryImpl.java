package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.persistence.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.airport.persistence.impl.AircraftRepositoryImpl.aircraftFields;
import static com.solvd.airport.persistence.impl.AirstripRepositoryImpl.airstripFields;
import static com.solvd.airport.persistence.impl.DirectionRepositoryImpl.directionFields;
import static com.solvd.airport.persistence.impl.PassengerRepositoryImpl.passengerJoins;
import static com.solvd.airport.persistence.impl.PilotRepositoryImpl.pilotFields;
import static com.solvd.airport.persistence.impl.TicketRepositoryImpl.ticketFields;
import static com.solvd.airport.persistence.impl.TicketRepositoryImpl.ticketJoins;

public class FlightRepositoryImpl implements FlightRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final PassportRepository passportRepository = new PassportRepositoryImpl();
    private static final AirstripRepository airstripRepository = new AirstripRepositoryImpl();
    private static final PilotRepository pilotRepository = new PilotRepositoryImpl();
    private static final AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
    private static final DirectionRepository directionRepository = new DirectionRepositoryImpl();
    private static final TicketRepository ticketRepository = new TicketRepositoryImpl();

    protected static final String flightFields =
            "f.id as flight_id, " +
                    "f.number as flight_number, " +
                    "f.date as flight_date, " +
                    directionFields + ", " +
                    pilotFields + ", " +
                    aircraftFields + ", " +
                    ticketFields + ", " +
                    airstripFields;

    protected static final String flightJoins =
            "left join tickets t on f.id = t.flight_id " +
                    ticketJoins +
                    "left join directions d on f.direction_id = d.id " +
                    "left join airstrips str on f.airstrip_id = str.id ";

    protected static final String flightReadAll =
            "select " +
                    flightFields +
                    "FROM flights f " +
                    flightJoins +
                    "left join aircrafts af on af.id = f.aircraft_id " +
                    "left join pilots pl on f.pilot_id = pl.id ";

    @Override
    public void create(Flight flight, Long aircarrierId) {
        System.out.println("REPOSITORY CREATE flight");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into flights(mode, number) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, flight.g());
            preparedStatement.setInt(2, flight.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            flight.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Flight> readById(Long id) {
        System.out.println("REPOSITORY READ flight by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Flight flight = new Flight();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    flightReadAll +
                            " where f.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            flight = map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(flight);
    }

    @Override
    public List<Flight> readAll() {
        System.out.println("REPOSITORY READ all flights");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Flight> flights;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    flightReadAll, Statement.RETURN_GENERATED_KEYS);
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
    public void update(Flight flight, Long aircarrierId) {
//        System.out.println("UPDATE flight");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update flights set name = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, flight.getModel());
//            preparedStatement.setInt(2, flight.getNumber());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE flight by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from flights where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
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
            if (flights == null) {
                flights = new ArrayList<>();
            }
            Flight flight = findById(id, flights);
            flight.setId(resultSet.getLong("flight_id"));
            flight.setAirstrip(airstripRepository.readById(resultSet.getLong("airstrip_id")).orElseGet(null));
            flight.setPilot(pilotRepository.readById(resultSet.getLong("pilot_id")).orElseGet(null));
            flight.setAircraft(aircraftRepository.readById(resultSet.getLong("aircraft_id")).orElseGet(null));
            flight.setDirection(directionRepository.readById(resultSet.getLong("direction_id")).orElseGet(null));
            flight.setTickets(TicketRepositoryImpl.mapRow(resultSet, flight.getTickets()));
            flight.setDate(resultSet.getDate("flight_date"));
            flight.setNumber(resultSet.getInt("flight_number"));
        }
        return flights;
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
}
