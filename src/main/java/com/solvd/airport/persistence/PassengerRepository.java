package com.solvd.airport.persistence;

import com.solvd.airport.domain.passenger.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository {

    void create(Passenger passenger);

    List<Passenger> readAll();

    Optional<Passenger> readById(Long id);

    void update(Passenger passenger);

    void deleteById(Long id);
}
