package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.persistence.AircarrierRepository;
import com.solvd.airport.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AircarrierMapperImpl implements AircarrierRepository {

    @Override
    public void create(Aircarrier aircarrier) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository aircarrierRepository = session.getMapper(AircarrierRepository.class);
            aircarrierRepository.create(aircarrier);
        }
    }

    @Override
    public List<Aircarrier> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository aircarrierRepository = session.getMapper(AircarrierRepository.class);
            return aircarrierRepository.readAll();
        }
    }

    @Override
    public Optional<Aircarrier> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository mapper = session.getMapper(AircarrierRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Aircarrier aircarrier) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository aircarrierRepository = session.getMapper(AircarrierRepository.class);
            aircarrierRepository.update(aircarrier);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository aircarrierRepository = session.getMapper(AircarrierRepository.class);
            aircarrierRepository.deleteById(id);
        }
    }
}
