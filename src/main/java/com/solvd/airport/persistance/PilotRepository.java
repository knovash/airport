package com.solvd.airport.persistance;

import com.solvd.airport.domain.carrier.Pilot;

import java.util.List;

public interface PilotRepository {

    void create(Pilot pilot);

    List<Pilot> readAll();

    Pilot readById(Long id);

    List<Pilot> readByAircarrierId(Long aircarrierId);

    void update(Pilot pilot);

    void deleteById(Long id);

    void deleteByNumber(Integer number);
}