package com.solvd.airport.persistance;

import com.solvd.airport.domain.passenger.Passport;

import java.util.List;

public interface PassportRepository {

    void create(Passport passport);

    List<Passport> read();

    void update(Passport passport);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}
