package com.solvd.airport.persistance.impl;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistance.ConnectionPool;
import com.solvd.airport.persistance.PassengerRepository;

import java.sql.*;

public class PassengerRepositoryImpl implements PassengerRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Passenger passenger) { // вызывается из сервиса. делает инсерт данных объекта в бд.
        Connection connection = CONNECTION_POOL.getConnection();
        // тут SQL запрос INSERT заносит в бд новое направление
        try { // INSERT
            PreparedStatement preparedStatement = connection.prepareStatement("insert into passengers(passport_id, name) values (?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, passenger.getPassport().getId());
            preparedStatement.setString(2, passenger.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passenger.setId(resultSet.getLong(1)); // в объект сетаем ид полученый из бд. с которым произошла запись
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
