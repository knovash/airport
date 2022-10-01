package com.solvd.airport.persistence;

import com.solvd.airport.domain.passenger.Passenger;

import java.util.List;

public interface PassengerRepository {

    void create(Passenger passenger);

    List<Passenger> readAll();

    Passenger readById(Long id);

    void update(Passenger passenger);

    void deleteById(Long id);
}
