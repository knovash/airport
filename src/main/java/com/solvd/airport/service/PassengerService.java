package com.solvd.airport.service;

import com.solvd.airport.domain.passenger.Passenger;

import java.util.List;

public interface PassengerService {

    Passenger create(Passenger passenger);

    List<Passenger> readAll();

}