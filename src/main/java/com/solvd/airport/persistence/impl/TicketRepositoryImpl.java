package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.flight.Ticket;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistence.*;
import com.solvd.airport.persistence.TicketRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Ticket ticket, Long flightId) {
        System.out.println("CREATE ticket");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into tickets(" +
                            "passenger_id, " +
                            "price, " +
                            "seat, " +
                            "flight_id" +
                            ") values (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, ticket.getPassenger().getId());
            preparedStatement.setBigDecimal(23, ticket.getPrice());
            preparedStatement.setLong(3, ticket.getSeat());
            preparedStatement.setLong(4, flightId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                ticket.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
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
            if (tickets == null) tickets = new ArrayList<>();
            Ticket ticket = findById(id, tickets);

            ticket.setId(resultSet.getLong("ticket_id"));
            ticket.setPrice(resultSet.getBigDecimal("price"));
            ticket.setSeat(resultSet.getInt("seat"));

            List<Passenger> passengers = PassengerRepositoryImpl.mapRow(resultSet, new ArrayList<>());
            Passenger passenger = passengers.get(0);
            ticket.setPassenger(passenger);

        }
        return tickets;
    }

    @Override
    public List<Ticket> readAll() {
        System.out.println("READ all tickets");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Ticket> tickets;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select\n" +
                            "tickets.id as ticket_id, \n" +
                            "tickets.price as price, \n" +
                            "tickets.seat as seat,\n" +
                            "aircarriers.id as aircarrier_id, \n" +
                            "aircarriers.name as aircarrier_name, \n" +
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
                            "passengers.id as passenger_id, \n" +
                            "passengers.name as passenger_name, \n" +
                            "passengers.passport_id as passport_id, \n" +
                            "passports.number as passport_number,\n" +
                            "pilots.id as pilot_id, \n" +
                            "pilots.name as pilot_name\n" +
                            "from tickets \n" +
                            "join flights on tickets.flight_id = flights.id\n" +
                            "join passengers on passengers.id = tickets.passenger_id\n" +
                            "join passports on passports.id = passengers.passport_id\n" +
                            "join aircarriers on flights.aircarrier_id = aircarriers.id \n" +
                            "join aircrafts on flights.aircraft_id = aircrafts.id \n" +
                            "join airstrips on flights.airstrip_id = airstrips.id \n" +
                            "join directions on flights.direction_id = directions.id \n" +
                            "join pilots on flights.pilot_id = pilots.id;", Statement.RETURN_GENERATED_KEYS);
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
    public Ticket readById(Long id) {
        System.out.println("READ ticket by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Ticket ticket = new Ticket();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select\n" +
                            " tickets.id as ticket_id,\n" +
                            " tickets.price as price,\n" +
                            " tickets.seat as seat,\n" +
                            " passengers.id as passenger_id,\n" +
                            " passengers.name as passenger_name,\n" +
                            " passengers.passport_id as passport_id,\n" +
                            " passports.number as passport_number, \n" +
                            " flights.id as flight_id,\n" +
                            " flights.number as flight_number, \n" +
                            " flights.date as flight_date,\n" +
                            " flights.aircarrier_id as flight_aircarrier_id,\n" +
                            " flights.aircraft_id as flight_aircraft_id,\n" +
                            " flights.airstrip_id as flight_airstrip_id,\n" +
                            " flights.direction_id as flight_direction_id,\n" +
                            " flights.pilot_id as flight_pilot_id,\n" +
                            " pilots.name as pilot_name,\n" +
                            " aircarriers.name as aircarrier_name,\n" +
                            " aircrafts.number as aircraft_number,\n" +
                            " aircrafts.model as aircraft_model,\n" +
                            " airstrips.number as airstrip_number,\n" +
                            " directions.country as country,\n" +
                            " directions.distance as distance\n" +
                            " from tickets\n" +
                            " join flights on tickets.flight_id = flights.id\n" +
                            " join passengers on tickets.passenger_id = passengers.id\n" +
                            " join passports on passengers.passport_id = passports.id \n" +
                            " join pilots on flights.pilot_id = pilots.id\n" +
                            " join aircarriers on flights.aircarrier_id = aircarriers.id\n" +
                            " join aircrafts on flights.aircraft_id = aircrafts.id\n" +
                            " join airstrips on flights.airstrip_id = airstrips.id\n" +
                            " join directions on flights.direction_id = directions.id;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                ticket = map(resultSet);
//            }
//            tickets = mapRow(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return ticket;
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
}
