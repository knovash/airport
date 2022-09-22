package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.carrier.PilotLicense;
import com.solvd.airport.persistance.ConnectionPool;
import com.solvd.airport.persistance.PilotLicenseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotLicenceRepositoryImpl implements PilotLicenseRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(PilotLicense pilotLicense) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        System.out.println("CREATE pilotLicense");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into pilotLicenses(number) values (?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, pilotLicense.getNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                pilotLicense.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public List<PilotLicense> readAll() {
        System.out.println("READ all pilotLicenses");
        Connection connection = CONNECTION_POOL.getConnection();
        List<PilotLicense> pilotLicenses = new ArrayList<>();
        PilotLicense pilotLicense;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as id, number as number from pilots_licenses;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pilotLicense = new PilotLicense();
                long id = resultSet.getLong("id");
                Integer number = resultSet.getInt("number");
                pilotLicense.setId(id);
                pilotLicense.setNumber(number);
                pilotLicenses.add(pilotLicense);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return pilotLicenses;

    }

    @Override
    public PilotLicense readById(Long id) {
        System.out.println("READ pilotLicense by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        PilotLicense pilotLicense = new PilotLicense();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as id, number as number from pilots_licenses where pilots_licenses.id =?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer number = resultSet.getInt("number");
                pilotLicense.setId(id);
                pilotLicense.setNumber(number);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return pilotLicense;
    }

    @Override
    public void update(PilotLicense pilotLicense) {
        System.out.println("UPDATE pilotLicense");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update pilotLicenses set number = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, pilotLicense.getNumber());
            preparedStatement.setLong(2, pilotLicense.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE pilotLicense by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from pilotLicenses where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteByNumber(Integer number) {
        System.out.println("DELETE pilotLicense by number=" + number);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from pilotLicenses where number = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }
}