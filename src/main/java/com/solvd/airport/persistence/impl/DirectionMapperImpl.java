package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.flight.Direction;
import com.solvd.airport.persistence.MybatisConfig;
import com.solvd.airport.persistence.DirectionRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
    public Direction readById(Long id) {
        System.out.println("MAPPER readById direction");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            DirectionRepository directionRepository = session.getMapper(DirectionRepository.class);
            return directionRepository.readById(id);
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
