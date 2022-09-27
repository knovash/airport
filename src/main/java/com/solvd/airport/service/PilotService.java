package com.solvd.airport.service;

import com.solvd.airport.domain.carrier.Pilot;

import java.util.List;

public interface PilotService {

    Pilot create(Pilot pilot, Long aircarrierId);

    List<Pilot> readAll();
}