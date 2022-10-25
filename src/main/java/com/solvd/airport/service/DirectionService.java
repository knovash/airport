package com.solvd.airport.service;

import com.solvd.airport.domain.flight.Direction;

import java.util.List;

public interface DirectionService {

    Direction create(Direction direction);

    List<Direction> readAll();

    Direction readById(Long id);

    void update(Direction direction);

    void deleteById(Long id);
}
