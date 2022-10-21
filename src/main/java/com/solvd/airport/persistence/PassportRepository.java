package com.solvd.airport.persistence;

import com.solvd.airport.domain.passenger.Passport;

import java.util.List;
import java.util.Optional;

public interface PassportRepository {

    void create(Passport passport);

    List<Passport> readAll();

    Optional<Passport> readById(Long id);

    void update(Passport passport);

    void deleteById(Long id);
}
