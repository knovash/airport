package com.solvd.airport.persistance;

import com.solvd.airport.domain.passenger.Passenger;

import java.util.List;

public interface PassengerRepository {

    void create(Passenger passenger, Long ticketId);

    List<Passenger> readAll();

    Passenger readById(Long id);

    void update(Passenger passenger);

    void deleteById(Long id);
}