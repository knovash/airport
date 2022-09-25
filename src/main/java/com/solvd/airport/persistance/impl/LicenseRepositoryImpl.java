package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.carrier.License;
import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.persistance.ConnectionPool;
import com.solvd.airport.persistance.LicenseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LicenseRepositoryImpl implements LicenseRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(License license) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        System.out.println("CREATE license");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into licenses(number) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, license.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                license.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }
    
    @Override
    public License map(ResultSet resultSet) throws SQLException {
        License license = new License();
        license.setId(resultSet.getLong("license_id"));
        license.setNumber(resultSet.getInt("license_number"));
        license.setType(resultSet.getString("licence_type"));
        return license;
    }
//    private Long id;
//    private Integer number;
//    private String type;
    
    @Override
    public List<License> readAll() {
        System.out.println("READ all licenses");
        Connection connection = CONNECTION_POOL.getConnection();
        List<License> licenses = new ArrayList<>();
        License license;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as license_id, number as license_number, type as licence_type from pilots_licenses;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                licenses.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return licenses;

    }

    @Override
    public License readById(Long id) {
        System.out.println("READ license by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        License license = new License();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as id, number as number from pilots_licenses where pilots_licenses.id =?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer number = resultSet.getInt("number");
                license.setId(id);
                license.setNumber(number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return license;
    }

    @Override
    public void update(License license) {
        System.out.println("UPDATE license");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update licenses set number = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, license.getNumber());
            preparedStatement.setLong(2, license.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE license by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from licenses where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteByNumber(Integer number) {
        System.out.println("DELETE license by number=" + number);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from licenses where number = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }
}