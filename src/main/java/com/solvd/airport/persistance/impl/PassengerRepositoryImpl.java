package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepositoryImpl implements PassengerRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final PassportRepository passportRepository = new PassportRepositoryImpl();

    @Override
    public void create(Passenger passenger) { // вызывается из сервиса. делает инсерт данных объекта в бд.
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
                passenger.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        CONNECTION_POOL.releaseConnection(connection);}
    }

    public List<Passenger> map(ResultSet resultSet) throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        while (resultSet.next()) {
            passengers.add(mapRow(resultSet));
        }
        return passengers;
    }

    public static Passenger mapRow(ResultSet resultSet) throws SQLException {
//        long id = resultSet.getLong("passenger_id");
Passenger passenger = new Passenger();
//        if (id != 0) {
//            if (passengers == null) {
//                passengers = new ArrayList<>();
//            }
//            Passenger passenger = findById(id, passengers);
            passenger.setId(resultSet.getLong("passenger_id"));
            passenger.setName(resultSet.getString("passenger_name"));
//            passport = passportRepository.map(resultSet);
//        }
        return passenger;
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

    @Override
    public List<Passenger> readAll() {
        System.out.println("READ all passengers");
        List<Passenger> passengers = new ArrayList<>();
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
//            while (resultSet.next()) {
//                passengers.add(map(resultSet));
//            }

            passengers = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return passengers;
    }

//    @Override
//    public Passenger map(ResultSet resultSet) throws SQLException {
//        Passenger passenger = new Passenger();
//        Passport passport;
//        passport = passportRepository.map(resultSet);
//        passenger.setId(resultSet.getLong("passenger_id"));
//        passenger.setName(resultSet.getString("passenger_name"));
//        passenger.setPassport(passport);
//        return passenger;
//    }

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