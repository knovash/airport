package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.persistence.MybatisConfig;
import com.solvd.airport.persistence.DirectionRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class DirectionMapperImpl implements DirectionRepository {

    @Override
    public void create(Direction direction) {
        System.out.println("MAPPER create direction");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            DirectionRepository directionRepository = session.getMapper(DirectionRepository.class);
            directionRepository.create(direction);
        }
    }

    @Override
    public List<Direction> readAll() {
        System.out.println("MAPPER readAll directions");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            DirectionRepository directionRepository = session.getMapper(DirectionRepository.class);
            return directionRepository.readAll();
        }
    }

    @Override
    public Optional<Direction> readById(Long id) {
        System.out.println("MAPPER readById direction");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            DirectionRepository mapper = session.getMapper(DirectionRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Direction direction) {
        System.out.println("MAPPER update direction");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            DirectionRepository directionRepository = session.getMapper(DirectionRepository.class);
            directionRepository.update(direction);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("MAPPER deleteById direction");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            DirectionRepository directionRepository = session.getMapper(DirectionRepository.class);
            directionRepository.deleteById(id);
        }
    }
}
