package com.solvd.airport.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private static ConnectionPool INSTANCE;
    private final BlockingQueue<Connection> availableConns = new LinkedBlockingQueue<>();
    private final BlockingQueue<Connection> usedConns = new LinkedBlockingQueue<>();

    private ConnectionPool(int poolSize) {
        System.out.println("Constructor: ConnectionPool size=" + poolSize);
        for (int i = 0; i < poolSize; i++) {
            availableConns.add(createConnection());
        }
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool(Config.getPoolsize());
        }
        return INSTANCE;
    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Config.getUrl(), Config.getUsername(), Config.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConns.take();
            usedConns.offer(connection);
        } catch (InterruptedException e) {
            System.out.println("try getConnection: error");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        usedConns.remove(connection);
        availableConns.offer(connection);
    }
}
