package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistance.*;
import com.solvd.airport.persistance.FlightRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final AircarrierRepository aircarrierRepository = new AircarrierRepositoryImpl();
    private static final AircraftRepository aircraftRepository = new AircraftRepositoryImpl();
    private static final AirstripRepository airstripRepository = new AirstripRepositoryImpl();
    private static final PilotRepository pilotRepository = new PilotRepositoryImpl();
    private static final DirectionRepository directionRepository = new DirectionRepositoryImpl();
    private static final TicketRepository ticketRepository = new TicketRepositoryImpl();

    @Override
    public void create(Flight flight) { // вызывается из сервиса. делает инсерт данных объекта в бд.
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
            preparedStatement.setLong(3, flight.getAircarrier().getId());
            preparedStatement.setLong(4, flight.getAircraft().getId());
            preparedStatement.setLong(5, flight.getAirstrip().getId());
            preparedStatement.setLong(6, flight.getDirection().getId());
            preparedStatement.setLong(7, flight.getPilot().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                flight.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        CONNECTION_POOL.releaseConnection(connection);}
    }


    public Flight map(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();

//        Aircraft aircraft;
//        aircraft = aircraftRepository.map(resultSet);
//        flight.setAircraft(aircraft);

//        Aircarrier aircarrier;
//        aircarrier = aircarrierRepository.map(resultSet);
//        flight.setAircarrier(aircarrier);

//        Airstrip airstrip;
//        airstrip = airstripRepository.map(resultSet);
//        flight.setAirstrip(airstrip);

//        Direction direction;
//        direction = directionRepository.map(resultSet);
//        flight.setDirection(direction);

//        List<Pilot> pilots;
//        pilots = pilotRepository.map(resultSet);
//        flight.setPilot(pilots);

//        List<Ticket> tickets = new ArrayList<>();
//        Ticket ticket;
//        ticket = ticketRepository.map(resultSet);
//        tickets.add(ticket);


//        flight.setTickets(tickets);
        flight.setId(resultSet.getLong("flight_id"));
        flight.setNumber(resultSet.getInt("number"));
        flight.setDate(resultSet.getDate("date"));
        return flight;
    }


    @Override
    public List<Flight> readAll() {
        System.out.println("READ all flights");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Flight> flights = new ArrayList<>();
        Flight flight;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select\n" +
                            " flights.id as flight_id,\n" +
                            " flights.aircarrier_id as carrier_id, \n" +
                            " flights.aircraft_id as aircraft_id, \n" +
                            " flights.airstrip_id as airstrip_id, \n" +
                            " flights.direction_id as direction_id, \n" +
                            " flights.number as number, \n" +
                            " flights.date as date, \n" +
                            " aircarriers.name as aircarrier_name,\n" +
                            " aircarriers.id as aircarrier_id,\n" +
                            " aircrafts.number as aircraft_number, \n" +
                            " aircrafts.model as model, \n" +
                            " airstrips.id as airstrip_id, \n" +
                            " airstrips.number as airstrip_number, \n" +
                            " directions.id as direction_id, \n" +
                            " directions.country as country, \n" +
                            " directions.distance as distance,\n" +
                            " pilots.id as pilot_id,\n" +
                            " pilots.name as pilot_name,\n" +
                            " pilots.aircarrier_id as pilot_aircarrier_id\n" +
                            " from flights \n" +
                            " join aircarriers on  flights.aircarrier_id = aircarriers.id\n" +
                            " join aircrafts on flights.aircraft_id = aircrafts.id \n" +
                            " join airstrips on flights.airstrip_id = airstrips.id \n" +
                            " join directions on flights.direction_id = directions.id\n" +
                            " join pilots on flights.pilot_id = pilots.id;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flights.add(map(resultSet));
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