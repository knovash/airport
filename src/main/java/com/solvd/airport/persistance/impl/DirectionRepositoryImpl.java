package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.persistance.ConnectionPool;
import com.solvd.airport.persistance.DirectionRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DirectionRepositoryImpl implements DirectionRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Direction direction) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        System.out.println("CREATE direction");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into directions(country, distance) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, direction.getCountry());
            preparedStatement.setBigDecimal(2, direction.getDistance());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                direction.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    public Direction map(ResultSet resultSet) throws SQLException {
        Direction direction = new Direction();
        direction.setId(resultSet.getLong("direction_id"));
        direction.setCountry(resultSet.getString("country"));
        direction.setDistance(resultSet.getBigDecimal("distance"));
        return direction;
    }

    @Override
    public List<Direction> readAll() {
        System.out.println("READ all directions");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Direction> directions = new ArrayList<>();
        Direction direction;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as direction_id, country as country, distance as distance from directions;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                directions.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return directions;
    }

    @Override
    public Direction readById(Long id) {
        System.out.println("READ direction by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        Direction direction = new Direction();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id as id, country as country, distance as distance from directions where directions.id =?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                direction = new Direction();
                String country = resultSet.getString("country");
                BigDecimal distance = resultSet.getBigDecimal("distance");
                direction.setId(id);
                direction.setCountry(country);
                direction.setDistance(distance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return direction;
    }

    @Override
    public void update(Direction direction) {
        System.out.println("UPDATE direction");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update directions set country = ?, distance = ? where id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, direction.getCountry());
            preparedStatement.setBigDecimal(2, direction.getDistance());
            preparedStatement.setLong(3, direction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("DELETE direction by id=" + id);
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from directions where id = ?; ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
    }
}