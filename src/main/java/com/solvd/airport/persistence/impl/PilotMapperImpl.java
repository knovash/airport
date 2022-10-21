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
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository mapper = session.getMapper(PilotRepository.class);
            mapper.create(pilot, aircarrierId);
        }
    }

    @Override
    public List<Pilot> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository mapper = session.getMapper(PilotRepository.class);
            return mapper.readAll();
        }
    }

    @Override
    public Optional<Pilot> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository mapper = session.getMapper(PilotRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Pilot pilot, Long aircarrierId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository mapper = session.getMapper(PilotRepository.class);
            mapper.update(pilot, aircarrierId);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PilotRepository mapper = session.getMapper(PilotRepository.class);
            mapper.deleteById(id);
        }
    }
}
