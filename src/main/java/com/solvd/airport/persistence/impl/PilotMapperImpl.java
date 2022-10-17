package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.persistence.PilotRepository;
import com.solvd.airport.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PilotMapperImpl implements PilotRepository {

    @Override
    public void create(Pilot pilot, Long aircarrierId) {
        System.out.println("MAPPER create pilot");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            pilotRepository.create(pilot, aircarrierId);
        }
    }

    @Override
    public List<Pilot> readAll() {
        System.out.println("MAPPER readAll pilots");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            return pilotRepository.readAll();
        }
    }

    @Override
    public Optional<Pilot> readById(Long id) {
        System.out.println("MAPPER readById pilot");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository mapper = session.getMapper(PilotRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Pilot pilot, Long aircarrierId) {
        System.out.println("MAPPER update pilot");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            pilotRepository.update(pilot, aircarrierId);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("MAPPER deleteById pilot");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            pilotRepository.deleteById(id);
        }
    }
}
