package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.domain.port.Gate;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.TicketRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final FlightRepository flightRepository = new FlightRepositoryImpl();
    private static final PassengerRepository passengerRepository = new PassengerRepositoryImpl();
    private static final GateRepository gateRepository = new GateRepositoryImpl();

    @Override
    public void create(Ticket ticket) { // вызывается из сервиса. делает инсерт данных объекта в бд.
//        System.out.println("CREATE ticket");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try { //insert into tickets(ticket_id, name) values (6, 'Denis');
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                   "insert into tickets(ticket_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
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
    public Ticket map(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();

//        Flight flight;
//        flight = flightRepository.map(resultSet);

        Passenger passenger;
        passenger = passengerRepository.map(resultSet);

//        Gate gate;
//        gate = gateRepository.map(resultSet);

        ticket.setPassenger(passenger);
        ticket.setFlight_number(resultSet.getBigDecimal("flight_number"));
        ticket.setGate_number(resultSet.getBigDecimal("gate_number"));

        ticket.setId(resultSet.getLong("ticket_id"));
        ticket.setPrice(resultSet.getBigDecimal("price"));
        ticket.setNumber(resultSet.getInt("number"));
        ticket.setSeat(resultSet.getInt("seat"));
        return ticket;
    }
//    private Long id;
//    private Flight flight;
//    private Passenger passenger;
//    private Gate gate;
//    private BigDecimal price;
//    private Integer number;
//    private Integer seat;
    
    @Override
    public List<Ticket> readAll() {
        System.out.println("READ all tickets");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Ticket> tickets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select \n" +
                            " tickets.id as ticket_id, \n" +
                            " flights.id as flight_id, \n" +
                            " flights.number as flight_number,\n" +
                            " passengers.id as passenger_id, \n" +
                            " passengers.name as passenger_name, \n" +
                            " passengers.passport_id as passport_id, \n" +
                            " passports.number as passport_number,\n" +
                            " gates.id as gate_id, \n" +
                            " gates.number as gate_number, \n" +
                            " tickets.price as price, \n" +
                            " tickets.number as number, \n" +
                            " tickets.seat as seat \n" +
                            " from tickets \n" +
                            " join flights on tickets.flight_id = flights.id \n" +
                            " join passengers on tickets.passenger_id = passengers.id \n" +
                            " join passports on passengers.passport_id = passports.id\n" +
                            " join gates on tickets.gate_id = gates.id;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(map(resultSet));
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
                   "select  " +
                           " tickets.id as ticket_id,  " +
                           " flights.id as flight_id,  " +
                           " passengers.id as passenger_id,  " +
                           " gates.id as gate_id,  " +
                           " tickets.price as price,  " +
                           " tickets.number as number,  " +
                           " tickets.seat as seat  " +
                           " from tickets  " +
                           " join flights on tickets.flight_id = flights.id  " +
                           " join passengers on tickets.passenger_id = passengers.id  " +
                           " join gates on tickets.gate_id = gates.id   " +
                           " where tickets.id =?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticket = map(resultSet);
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
                   "select  " +
                           " tickets.id as ticket_id,  " +
                           " flights.id as flight_id,  " +
                           " passengers.id as passenger_id,  " +
                           " gates.id as gate_id,  " +
                           " tickets.price as price,  " +
                           " tickets.number as number,  " +
                           " tickets.seat as seat  " +
                           " from tickets  " +
                           " join flights on tickets.flight_id = flights.id  " +
                           " join passengers on tickets.passenger_id = passengers.id  " +
                           " join gates on tickets.gate_id = gates.id   " +
                           " where flights.id =?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, flightId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ticket = new Ticket();
                ticket.setId(resultSet.getLong("ticket_id"));
//                ticket.setFlight(flightRepository.readById(resultSet.getLong("flight_id")));
                ticket.setPassenger(passengerRepository.readById(resultSet.getLong("passenger_id")));
//                ticket.setGate(gateRepository.readById(resultSet.getLong("gate_id")));
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
//                   "update tickets set name = ?, set ticket_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
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
//                   "delete from tickets where id = ?; ", Statement.RETURN_GENERATED_KEYS);
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
//                   "delete from tickets where number = ?; ", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, number);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }
}