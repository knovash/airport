package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.TicketRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    FlightRepository flightRepository = new FlightRepositoryImpl();
    PassengerRepository passengerRepository = new PassengerRepositoryImpl();
    GateRepository gateRepository = new GateRepositoryImpl();

    @Override
    public void create(Ticket ticket) { // вызывается из сервиса. делает инсерт данных объекта в бд.
//        System.out.println("\nCREATE ticket");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try { //insert into tickets(ticket_id, name) values (6, 'Denis');
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "insert into tickets(ticket_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, ticket.getTicket().getId());
//            preparedStatement.setString(2, ticket.getName());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            while (resultSet.next()) {
//                ticket.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<Ticket> readAll() {
        System.out.println("READ all tickets");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select\n" +
                            "tickets.id as ticket_id, \n" +
                            "flights.id as flight_id,\n" +
                            "passengers.id as passenger_id,\n" +
                            "gates.id as gate_id,\n" +
                            "tickets.price as price,\n" +
                            "tickets.number as number,\n" +
                            "tickets.seat as seat\n" +
                            "from tickets \n" +
                            "join flights on tickets.flight_id = flights.id\n" +
                            "join passengers on tickets.passenger_id = passengers.id\n" +
                            "join gates on tickets.gate_id = gates.id;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//        private Long id;
//        private Flight flight;
//        private Passenger passenger;
//        private Gate gate;
//        private BigDecimal price;
//        private Integer number;
//        private Integer seat;
                ticket = new Ticket();
                ticket.setId(resultSet.getLong("ticket_id"));
                ticket.setFlight(flightRepository.readById(resultSet.getLong("flight_id")));
                ticket.setPassenger(passengerRepository.readById(resultSet.getLong("passenger_id")));
                ticket.setGate(gateRepository.readById(resultSet.getLong("gate_id")));
                ticket.setPrice(resultSet.getBigDecimal("price"));
                ticket.setNumber(resultSet.getInt("number"));
                ticket.setSeat(resultSet.getInt("seat"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return tickets;
    }

    @Override
    public Ticket readById(Long id) {
        System.out.println("READ ticket by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Ticket ticket = new Ticket();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select\n" +
                            "tickets.id as ticket_id, \n" +
                            "flights.id as flight_id,\n" +
                            "passengers.id as passenger_id,\n" +
                            "gates.id as gate_id,\n" +
                            "tickets.price as price,\n" +
                            "tickets.number as number,\n" +
                            "tickets.seat as seat\n" +
                            "from tickets \n" +
                            "join flights on tickets.flight_id = flights.id\n" +
                            "join passengers on tickets.passenger_id = passengers.id\n" +
                            "join gates on tickets.gate_id = gates.id\n" +
                            "where tickets.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticket = new Ticket();
                ticket.setId(resultSet.getLong("ticket_id"));
                ticket.setFlight(flightRepository.readById(resultSet.getLong("flight_id")));
                ticket.setPassenger(passengerRepository.readById(resultSet.getLong("passenger_id")));
                ticket.setGate(gateRepository.readById(resultSet.getLong("gate_id")));
                ticket.setPrice(resultSet.getBigDecimal("price"));
                ticket.setNumber(resultSet.getInt("number"));
                ticket.setSeat(resultSet.getInt("seat"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return ticket;
    }

    @Override
    public List<Ticket> readByFlightId(Long flightId) {
        System.out.println("READ all tickets");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "passengers.id as passenger_id, \n" +
                            "gates.id as gate_id,\n" +
                            "                            tickets.price as price,\n" +
                            "                            tickets.number as number,\n" +
                            "                            tickets.seat as seat\n" +
                            "                            from tickets \n" +
                            "                            join flights on tickets.flight_id = flights.id\n" +
                            "                            join passengers on tickets.passenger_id = passengers.id\n" +
                            "                            join gates on tickets.gate_id = gates.id\n" +
                            "                            where flights.id =?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticket = new Ticket();
                ticket.setId(resultSet.getLong("ticket_id"));
                ticket.setFlight(flightRepository.readById(resultSet.getLong("flight_id")));
                ticket.setPassenger(passengerRepository.readById(resultSet.getLong("passenger_id")));
                ticket.setGate(gateRepository.readById(resultSet.getLong("gate_id")));
                ticket.setPrice(resultSet.getBigDecimal("price"));
                ticket.setNumber(resultSet.getInt("number"));
                ticket.setSeat(resultSet.getInt("seat"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return tickets;
    }

    @Override
    public void update(Ticket ticket) {
//        System.out.println("UPDATE ticket");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update tickets set name = ?, set ticket_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, ticket.getName());
//            preparedStatement.setLong(2, ticket.getTicket().getId());
//            preparedStatement.setLong(3, ticket.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
//        System.out.println("DELETE ticket by id=" + id);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from tickets where id = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteByNumber(Integer number) {
//        System.out.println("DELETE ticket by number=" + number);
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "delete from tickets where number = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, number);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}