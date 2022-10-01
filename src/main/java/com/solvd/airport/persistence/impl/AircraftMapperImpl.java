package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Aircraft;
import com.solvd.airport.persistence.MybatisConfig;
import com.solvd.airport.persistence.AircraftRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AircraftMapperImpl implements AircraftRepository {

    @Override
    public void create(Aircraft aircraft, Long aircarrierId) {
        System.out.println("MAPPER create aircraft");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.create(aircraft, aircarrierId);
        }
    }

    @Override
    public List<Aircraft> readAll() {
        System.out.println("MAPPER readAll aircrafts");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            return aircraftRepository.readAll();
        }
    }

    @Override
    public Aircraft readById(Long id) {
        System.out.println("MAPPER readById aircraft");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            return aircraftRepository.readById(id);
        }
    }
//
    @Override
    public void update(Aircraft aircraft, Long aircarrierId) {
        System.out.println("MAPPER update aircraft");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.update(aircraft, aircarrierId);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("MAPPER deleteById aircraft");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.deleteById(id);
        }
    }
}
