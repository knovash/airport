package com.solvd.airport.service;

import com.solvd.airport.domain.flight.Direction;

import java.util.List;

public interface DirectionService {

    Direction create(Direction direction);

    List<Direction> readAll();
}