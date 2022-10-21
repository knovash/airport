package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.port.AirportAircarrier;
import com.solvd.airport.persistence.AirportAircarrierRepository;
import com.solvd.airport.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AirportAircarrierMapperImpl implements AirportAircarrierRepository {

    @Override
    public void create(AirportAircarrier airportAircarrier) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            mapper.create(airportAircarrier);
        }
    }

    @Override
    public List<AirportAircarrier> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            return mapper.readAll();
        }
    }

    @Override
    public Optional<AirportAircarrier> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(AirportAircarrier airportAircarrier) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            mapper.update(airportAircarrier);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            mapper.deleteById(id);
        }
    }
}
