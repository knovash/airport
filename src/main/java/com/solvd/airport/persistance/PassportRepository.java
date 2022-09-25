package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.domain.passenger.Passport;

import java.sql.ResultSet;
import java.util.List;

public interface PassportRepository {

    void create(Passport passport);

    List<Passport> readAll();

    Passport readById(Long id);

    void update(Passport passport);

    void deleteById(Long id);
}