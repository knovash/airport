package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.FlightRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
    AirstripRepository airstripRepository = new AirstripRepositoryImpl();
    DirectionRepository directionRepository = new DirectionRepositoryImpl();
    TicketRepository ticketRepository = new TicketRepositoryImpl();
//                private Long id;
//                private Aircraft aircraft;
//                private Airstrip airstrip;
//                private Direction direction;
//                private Integer number;
//                private LocalDate date;
//                private List<Ticket> tickets;

    @Override
    public void create(Flight flight) { // вызывается из сервиса. делает инсерт данных объекта в бд.
//        System.out.println("\nCREATE flight");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try { //insert into flights(flight_id, name) values (6, 'Denis');
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into flights(flight_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, flight.getFlight().getId());
//            preparedStatement.setString(2, flight.getName());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            while (resultSet.next()) {
//                flight.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<Flight> readAll() {
        System.out.println("READ all flights");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Flight> flights = new ArrayList<>();
        Flight flight;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select \n" +
                            " flights.id as flight_id, \n" +
                            " flights.aircarrier_id as carrier_id,\n" +
                            " flights.aircraft_id as aircraft_id,\n" +
                            " flights.airstrip_id as airstrip_id,\n" +
                            " flights.direction_id as direction_id,\n" +
                            " flights.number as number,\n" +
                            " flights.date as date \n" +
                            " from flights;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                private Long id;
//                private Aircraft aircraft;
//                private Airstrip airstrip;
//                private Direction direction;
//                private Integer number;
//                private LocalDate date;
//                private List<Ticket> tickets;
                flight = new Flight();
                flight.setId(resultSet.getLong("flight_id"));
                flight.setAircraft(aircraftRepository.readById(resultSet.getLong("aircraft_id")));
                flight.setAirstrip(airstripRepository.readById(resultSet.getLong("airstrip_id")));
                flight.setDirection(directionRepository.readById(resultSet.getLong("direction_id")));
                flight.setNumber(resultSet.getInt("number"));
                flight.setDate(resultSet.getTimestamp("date").toLocalDateTime().toLocalDate());
                flight.setTickets(ticketRepository.readByFlightId(resultSet.getLong("flight_id")));
                flights.add(flight);
            }
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
                    "select \n" +
                            " flights.id as flight_id, \n" +
                            " flights.aircarrier_id as carrier_id,\n" +
                            " flights.aircraft_id as aircraft_id,\n" +
                            " flights.airstrip_id as airstrip_id,\n" +
                            " flights.direction_id as direction_id,\n" +
                            " flights.number as number,\n" +
                            " flights.date as date \n" +
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
                flight.setDate(resultSet.getTimestamp("date").toLocalDateTime().toLocalDate());
                flight.setTickets(ticketRepository.readByFlightId(resultSet.getLong("flight_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return flight;
    }

    @Override
    public List<Flight> readByPilotId(Long pilotId) {
        System.out.println("READ all flights");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Flight> flights = new ArrayList<>();
        Flight flight;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    " select \n" +
                            " flights.id as flight_id, \n" +
                            " flights.aircarrier_id as carrier_id,\n" +
                            " flights.aircraft_id as aircraft_id,\n" +
                            " flights.airstrip_id as airstrip_id,\n" +
                            " flights.direction_id as direction_id,\n" +
                            " flights.number as number,\n" +
                            " flights.date as date ,\n" +
                            " pilot_flights.pilot_id as pilot_id\n" +
                            " from flights\n" +
                            " join pilot_flights on flights.id = pilot_flights.flight_id \n" +
                            " where pilot_flights.pilot_id =?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, pilotId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                private Long id;
//                private Aircraft aircraft;
//                private Airstrip airstrip;
//                private Direction direction;
//                private Integer number;
//                private LocalDate date;
//                private List<Ticket> tickets;
                flight = new Flight();
                flight.setId(resultSet.getLong("flight_id"));
                flight.setAircraft(aircraftRepository.readById(resultSet.getLong("aircraft_id")));
                flight.setAirstrip(airstripRepository.readById(resultSet.getLong("airstrip_id")));
                flight.setDirection(directionRepository.readById(resultSet.getLong("direction_id")));
                flight.setNumber(resultSet.getInt("number"));
                flight.setDate(resultSet.getTimestamp("date").toLocalDateTime().toLocalDate());
                flight.setTickets(ticketRepository.readByFlightId(resultSet.getLong("flight_id")));
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return flights;
    }


    @Override
    public List<Flight> readByAircarrierId(Long aircarrierId) {
        System.out.println("READ all flights");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Flight> flights = new ArrayList<>();
        Flight flight;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    " select \n" +
                            " flights.id as flight_id, \n" +
                            " flights.aircarrier_id as carrier_id,\n" +
                            " flights.aircraft_id as aircraft_id,\n" +
                            " flights.airstrip_id as airstrip_id,\n" +
                            " flights.direction_id as direction_id,\n" +
                            " flights.number as number,\n" +
                            " flights.date as date \n" +
                            " from flights where flights.aircarrier_id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, aircarrierId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flight = new Flight();
                flight.setId(resultSet.getLong("flight_id"));
                flight.setAircraft(aircraftRepository.readById(resultSet.getLong("aircraft_id")));
                flight.setAirstrip(airstripRepository.readById(resultSet.getLong("airstrip_id")));
                flight.setDirection(directionRepository.readById(resultSet.getLong("direction_id")));
                flight.setNumber(resultSet.getInt("number"));
                flight.setDate(resultSet.getTimestamp("date").toLocalDateTime().toLocalDate());
                flight.setTickets(ticketRepository.readByFlightId(resultSet.getLong("flight_id")));
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return flights;
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

    @Override
    public void deleteByNumber(Integer number) {
//        System.out.println("DELETE flight by number=" + number);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from flights where number = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, number);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}