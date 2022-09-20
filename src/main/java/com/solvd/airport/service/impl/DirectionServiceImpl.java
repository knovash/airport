package com.solvd.airport.service.impl;


import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.persistance.DirectionRepository;
import com.solvd.airport.persistance.impl.DirectionRepositoryImpl;
import com.solvd.airport.service.DirectionService;

public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    public DirectionServiceImpl() {
        this.directionRepository = new DirectionRepositoryImpl();
    }

    @Override
    public Direction create(Direction direction) {
        direction.setId(null);
        directionRepository.create(direction); // directionRepository в persistance там sql insert зааносит информацию из полей в бд
        return direction;
    }
}
