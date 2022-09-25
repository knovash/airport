package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.License;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface LicenseRepository {

    void create(License license);

    List<License> readAll();

    License map(ResultSet resultSet) throws SQLException;

    License readById(Long id);

    void update(License license);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}