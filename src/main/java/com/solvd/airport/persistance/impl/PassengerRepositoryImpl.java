package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepositoryImpl implements PassengerRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Passenger passenger, Long ticketId) {
        System.out.println(" CREATE passenger");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into passengers(passport_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, passenger.getPassport().getId());
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passenger.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        CONNECTION_POOL.releaseConnection(connection);}
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
            if (passengers == null) passengers = new ArrayList<>();
            Passenger passenger = findById(id, passengers);

            passenger.setId(resultSet.getLong("passenger_id"));
            passenger.setName(resultSet.getString("passenger_name"));

            List<Passport> passports = PassportRepositoryImpl.mapRow(resultSet, new ArrayList<>());
            passenger.setPassport(passports.get(0));
        }
        return passengers;
    }

    @Override
    public List<Passenger> readAll() {
        System.out.println("READ all passengers");
        List<Passenger> passengers;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select \n" +
                            "passengers.id as passenger_id, \n" +
                            "passengers.name as passenger_name, \n" +
                            "passengers.passport_id as passport_id, \n" +
                            "passports.number as passport_number \n" +
                            "from passengers \n" +
                            "join passports on passengers.passport_id = passports.id;", Statement.RETURN_GENERATED_KEYS);
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
    public Passenger readById(Long id) {
        System.out.println("READ passenger by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Passenger passenger = new Passenger();
        Passport passport;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select \n" +
                            "passengers.id as passenger_id, \n" +
                            "passengers.name as name, \n" +
                            "passengers.passport_id as passport_id, \n" +
                            "passports.number as passport_number \n" +
                            "from passengers \n" +
                            "join passports on passengers.passport_id = passports.id\n" +
                            "where passengers.id = 1;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passenger = new Passenger();
                passport = new Passport();
                passenger.setId(resultSet.getLong("passenger_id"));
                passenger.setName(resultSet.getString("name"));
                passport.setId(resultSet.getLong("passport_id"));
                passport.setNumber(resultSet.getInt("passport_number"));
                passenger.setPassport(passport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return passenger;
    }

    @Override
    public void update(Passenger passenger) {
        System.out.println("UPDATE passenger");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update passengers set name = ?, set passport_id = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setLong(2, passenger.getPassport().getId());
            preparedStatement.setLong(3, passenger.getId());
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
}