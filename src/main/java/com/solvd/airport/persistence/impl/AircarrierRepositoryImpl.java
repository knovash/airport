package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.PassportRepository;
import com.solvd.airport.persistence.AircarrierRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.airport.persistence.impl.AircraftRepositoryImpl.aircraftFields;
import static com.solvd.airport.persistence.impl.FlightRepositoryImpl.flightFields;
import static com.solvd.airport.persistence.impl.FlightRepositoryImpl.flightJoins;
import static com.solvd.airport.persistence.impl.PassengerRepositoryImpl.passengerFields;
import static com.solvd.airport.persistence.impl.PassengerRepositoryImpl.passengerJoins;
import static com.solvd.airport.persistence.impl.PassportRepositoryImpl.passportFields;
import static com.solvd.airport.persistence.impl.PilotRepositoryImpl.pilotFields;

public class AircarrierRepositoryImpl implements AircarrierRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final PassportRepository passportRepository = new PassportRepositoryImpl();
    private static final PassengerRepository passengerRepository = new PassengerRepositoryImpl();

    protected static final String aircarrierFields =
            "ac.id as aircarrier_id, " +
                    "ac.name as aircarrier_name, " +
                    flightFields + ", " +
                    aircraftFields + ", " +
                    pilotFields;

    protected static final String aircarrierJoins =
            "left join flights f on ac.id = f.aircarrier_id " +
                    flightJoins +
                    "left join aircrafts af on ac.id = af.aircarrier_id " +
                    "left join pilots pl on ac.id = pl.aircarrier_id ";

    protected static final String aircarrierReadAll =
            "select " +
                    aircarrierFields +
                    "FROM aircarriers ac " +
                    aircarrierJoins;

    @Override
    public void create(Aircarrier aircarrier) {
        System.out.println("REPOSITORY CREATE aircarrier");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into aircarriers(name, passport_id) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aircarrier.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            aircarrier.setId(resultSet.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Aircarrier> readById(Long id) {
        System.out.println("REPOSITORY READ aircarrier by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Aircarrier aircarrier = new Aircarrier();
        List<Aircarrier> aircarriers;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    aircarrierReadAll +
                            " where ac.id=?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            aircarrier = map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(aircarrier);
    }

    @Override
    public List<Aircarrier> readAll() {
        System.out.println("REPOSITORY READ all aircarriers");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Aircarrier> aircarriers;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    aircarrierReadAll, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            aircarriers = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return aircarriers;
    }

    @Override
    public void update(Aircarrier aircarrier) {
        System.out.println("UPDATE aircarrier");
//        Connection connection = CONNECTION_POOL.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "update aircarriers set name = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, aircarrier.getName());
//            preparedStatement.setLong(2, aircarrier.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        CONNECTION_POOL.releaseConnection(connection);
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
            if (aircarriers == null) {
                aircarriers = new ArrayList<>();
            }
            Aircarrier aircarrier = findById(id, aircarriers);
            aircarrier.setName(resultSet.getString("aircarrier_name"));
            aircarrier.setAircrafts(AircraftRepositoryImpl.mapRow(resultSet, aircarrier.getAircrafts()));
            aircarrier.setPilots(PilotRepositoryImpl.mapRow(resultSet, aircarrier.getPilots()));
            aircarrier.setFlights(FlightRepositoryImpl.mapRow(resultSet, aircarrier.getFlights()));
        }
        return aircarriers;
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
}
