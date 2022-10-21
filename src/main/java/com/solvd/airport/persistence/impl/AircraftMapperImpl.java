package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.persistence.MybatisConfig;
import com.solvd.airport.persistence.AircraftRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AircraftMapperImpl implements AircraftRepository {

    @Override
    public void create(Aircraft aircraft, Long aircarrierId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.create(aircraft, aircarrierId);
        }
    }

    @Override
    public List<Aircraft> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            return aircraftRepository.readAll();
        }
    }

    @Override
    public Optional<Aircraft> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository mapper = session.getMapper(AircraftRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Aircraft aircraft, Long aircarrierId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.update(aircraft, aircarrierId);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.deleteById(id);
        }
    }
}
