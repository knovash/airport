package com.solvd.airport.service;

import com.solvd.airport.domain.carrier.Aircarrier;

import java.util.List;

public interface AircarrierService {

    Aircarrier create(Aircarrier aircarrier);

    List<Aircarrier> readAll();

}