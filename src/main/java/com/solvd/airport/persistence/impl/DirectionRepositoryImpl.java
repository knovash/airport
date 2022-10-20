package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.DirectionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectionRepositoryImpl implements DirectionRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    protected static final String directionFields =
            "d.id as direction_id, " +
                    "        d.country as country, " +
                    "        d.distance as distance ";

    protected static final String directionReadAll =
            "select " +
                    directionFields +
                    "from directions d ";

    @Override
    public void create(Direction direction) {
        System.out.println("REPOSITORY CREATE direction");
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into directions(name, passport_id) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, direction.getCountry());
            preparedStatement.setBigDecimal(2, direction.getDistance());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            direction.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Direction> readById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Direction direction;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    directionReadAll +
                            " where d.id = ?;", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            direction = map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return Optional.of(direction);
    }

    @Override
    public List<Direction> readAll() {
        System.out.println("REPOSITORY READ all directions");
        Connection connection = CONNECTION_POOL.getConnection();
        List<Direction> directions;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    directionReadAll, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            directions = map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CONNECTION_POOL.releaseConnection(connection);
        return directions;
    }

    @Override
    public void update(Direction direction) {
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

    public List<Direction> map(ResultSet resultSet) throws SQLException {
        List<Direction> directions = new ArrayList<>();
        while (resultSet.next()) {
            directions = mapRow(resultSet, directions);
        }
        return directions;
    }

    public static List<Direction> mapRow(ResultSet resultSet, List<Direction> directions) throws SQLException {
        long id = resultSet.getLong("direction_id");
        if (id != 0) {
            if (directions == null) {
                directions = new ArrayList<>();
            }
            Direction direction = findById(id, directions);
            direction.setId(resultSet.getLong("direction_id"));
            direction.setCountry(resultSet.getString("country"));
            direction.setDistance(resultSet.getBigDecimal("distance"));
        }
        return directions;
    }

    private static Direction findById(Long id, List<Direction> directions) {
        return directions.stream()
                .filter(direction -> direction.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Direction newDirection = new Direction();
                    newDirection.setId(id);
                    directions.add(newDirection);
                    return newDirection;
                });
    }
}
