package com.solvd.airport.persistance;

import com.solvd.airport.domain.passenger.Passenger;

public interface PassengerRepository {

    public void create(Passenger passenger);

}
