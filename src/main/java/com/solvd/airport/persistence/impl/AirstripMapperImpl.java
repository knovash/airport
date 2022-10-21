package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistence.MybatisConfig;
import com.solvd.airport.persistence.AirstripRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AirstripMapperImpl implements AirstripRepository {

    @Override
    public void create(Airstrip airstrip, Long airportId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            airstripRepository.create(airstrip, airportId);
        }
    }

    @Override
    public List<Airstrip> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            return airstripRepository.readAll();
        }
    }

    @Override
    public Optional<Airstrip> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository mapper = session.getMapper(AirstripRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Airstrip airstrip, Long airportId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            airstripRepository.update(airstrip, airportId);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            airstripRepository.deleteById(id);
        }
    }
}
