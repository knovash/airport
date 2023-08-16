package com.solvd.airport.service.impl;

import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.persistence.DirectionRepository;
import com.solvd.airport.persistence.impl.DirectionMapperImpl;
import com.solvd.airport.service.DirectionService;

import java.util.List;

public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    public DirectionServiceImpl() {
        this.directionRepository = new DirectionMapperImpl();
    }

    @Override
    public Direction create(Direction direction) {
        System.out.println("SERVICE create direction");
        direction.setId(null);
        directionRepository.create(direction);
        return direction;
    }

    @Override
    public List<Direction> readAll() {
        System.out.println("SERVICE readAll directions");
        return directionRepository.readAll();
    }

    @Override
    public Direction readById(Long id) {
        System.out.println("SERVICE readById direction");
        return directionRepository.readById(id)
                .orElseThrow(() -> new NotFound("Direction with id=" + id + " not found"));
    }

    @Override
    public void update(Direction direction) {
        System.out.println("SERVICE update direction");
        directionRepository.update(direction);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById direction");
        directionRepository.deleteById(id);
    }
}
