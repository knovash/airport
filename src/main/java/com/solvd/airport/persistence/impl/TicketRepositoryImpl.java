package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.TicketRepository;
import com.solvd.airport.persistence.PassportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.airport.persistence.impl.PassengerRepositoryImpl.passengerFields;
import static com.solvd.airport.persistence.impl.PassengerRepositoryImpl.passengerJoins;
import static com.solvd.airport.persistence.impl.PassportRepositoryImpl.passportFields;

public class TicketRepositoryImpl implements TicketRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final PassportRepository passportRepository = new PassportRepositoryImpl();
    private static final PassengerRepository passengerRepository = new PassengerRepositoryImpl();

    protected static final String ticketFields =
            "t.id as ticket_id, " +
                    "t.price as ticket_price, " +
                    "t.seat as ticket_seat, " +
                    passportFields + "," +
                    passengerFields;

    protected static final String ticketJoins =
            "left join passengers pr on t.passenger_id = pr.id " +
                    passengerJoins;

    protected static final String ticketReadAll =
            "select " +
                    ticketFields +
                    "FROM tickets t " +
                    ticketJoins;

    @Override
    public void create(Ticket ticket, Long aircarrierId) {
        System.out.println("REPOSITORY CREATE ticket");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into tickets(name, passport_id) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBigDecimal(1, ticket.getPrice());
            preparedStatement.setLong(1, ticket.getSeat());
            preparedStatement.setLong(2, ticket.getPassenger().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            ticket.setId(resultSet.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Ticket> readById(Long id) {
        System.out.println("REPOSITORY READ ticket by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Ticket ticket = new Ticket();
        List<Ticket> tickets;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    ticketReadAll +
                            " where t.id=?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            ticket = map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(ticket);
    }

    @Override
    public List<Ticket> readAll() {
        System.out.println("REPOSITORY READ all tickets");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Ticket> tickets;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    ticketReadAll, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            tickets = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return tickets;
    }

    @Override
    public void update(Ticket ticket, Long aircarrierId) {
        System.out.println("UPDATE ticket");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update tickets set name = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, ticket.getName());
//            preparedStatement.setLong(2, ticket.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE ticket by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from tickets where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    public List<Ticket> map(ResultSet resultSet) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        while (resultSet.next()) {
            tickets = mapRow(resultSet, tickets);
        }
        return tickets;
    }

    public static List<Ticket> mapRow(ResultSet resultSet, List<Ticket> tickets) throws SQLException {
        long id = resultSet.getLong("ticket_id");
        if (id != 0) {
            if (tickets == null) {
                tickets = new ArrayList<>();
            }
            Ticket ticket = findById(id, tickets);
            ticket.setId(resultSet.getLong("ticket_id"));
            ticket.setPrice(resultSet.getBigDecimal("ticket_price"));
            ticket.setSeat(resultSet.getInt("ticket_seat"));
            ticket.setPassenger(passengerRepository.readById(resultSet.getLong("passenger_id")).orElseGet(null));

        }
        return tickets;
    }

    private static Ticket findById(Long id, List<Ticket> tickets) {
        return tickets.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Ticket newTicket = new Ticket();
                    newTicket.setId(id);
                    tickets.add(newTicket);
                    return newTicket;
                });
    }
}
