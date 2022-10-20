package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.PassportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.solvd.airport.persistence.impl.PassportRepositoryImpl.passportFields;

public class PassengerRepositoryImpl implements PassengerRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final PassportRepository passportRepository = new PassportRepositoryImpl();

    protected static final String passengerFields =
            "pr.id as passenger_id, " +
                    "pr.name as name, " + passportFields;

    protected static final String passengerJoins =
            "left join passports pt on pr.passport_id = pt.id ";

    protected static final String passengerReadAll =
            "select " +
                    passengerFields +
                    "from passengers pr " +
                    passengerJoins;

    @Override
    public void create(Passenger passenger) {
        System.out.println("REPOSITORY CREATE passenger");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into passengers(name, passport_id) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setLong(2, passenger.getPassport().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            passenger.setId(resultSet.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Passenger> readById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Passenger passenger;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    passengerReadAll +
                            " where pr.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            passenger = map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(passenger);
    }

    @Override
    public List<Passenger> readAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Passenger> passengers;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    passengerReadAll, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            passengers = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return passengers;
    }

    @Override
    public void update(Passenger passenger) {
        System.out.println("UPDATE passenger");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update passengers set name = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setLong(2, passenger.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE passenger by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from passengers where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    public List<Passenger> map(ResultSet resultSet) throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        while (resultSet.next()) {
            passengers = mapRow(resultSet, passengers);
        }
        return passengers;
    }

    public static List<Passenger> mapRow(ResultSet resultSet, List<Passenger> passengers) throws SQLException {
        long id = resultSet.getLong("passenger_id");
        if (id != 0) {
            if (passengers == null) {
                passengers = new ArrayList<>();
            }
            Passenger passenger = findById(id, passengers);
            passenger.setId(resultSet.getLong("passenger_id"));
            passenger.setName(resultSet.getString("name"));
            passenger.setPassport(passportRepository.readById(resultSet.getLong("passport_id")).orElseGet(null));

        }
        return passengers;
    }

    private static Passenger findById(Long id, List<Passenger> passengers) {
        return passengers.stream()
                .filter(passenger -> passenger.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Passenger newPassenger = new Passenger();
                    newPassenger.setId(id);
                    passengers.add(newPassenger);
                    return newPassenger;
                });
    }
}
