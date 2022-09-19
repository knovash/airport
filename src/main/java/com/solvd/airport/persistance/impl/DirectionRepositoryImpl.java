package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.persistance.ConnectionPool;
import com.solvd.airport.persistance.DirectionRepository;

import java.sql.Connection;
import java.sql.*;

public class DirectionRepositoryImpl implements DirectionRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Direction direction) { // вызывается из сервиса
        Connection connection = CONNECTION_POOL.getConnection();
        // тут SQL запрос INSERT заносит в бд новое направление
        try { // INSERT
            PreparedStatement preparedStatement = connection.prepareStatement("insert into directions(country, distance) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
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
    }
}
