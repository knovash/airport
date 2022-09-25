package com.solvd.airport.service.impl;


import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.persistance.DirectionRepository;
import com.solvd.airport.persistance.impl.DirectionRepositoryImpl;
import com.solvd.airport.service.DirectionService;

import java.util.List;

public class PassportServiceImpl implements DirectionService {

    private DirectionRepository directionRepository = new DirectionRepositoryImpl();

    public PassportServiceImpl() {
        this.directionRepository = new DirectionRepositoryImpl();

    }

    @Override
    public Direction create(Direction direction) {
        direction.setId(null);
        directionRepository.create(direction); // directionRepository в persistance там sql insert зааносит информацию из полей в бд
        return direction;
    }

    @Override
    public List<Direction> readAll() {
        return null;
    }
}
