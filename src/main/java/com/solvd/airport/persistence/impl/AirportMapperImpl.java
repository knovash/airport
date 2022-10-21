package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.port.Airport;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AirportMapperImpl implements AirportRepository {

    @Override
    public void create(Airport airport) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            airportRepository.create(airport);
        }
    }

    @Override
    public List<Airport> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            return airportRepository.readAll();
        }
    }

    @Override
    public Optional<Airport> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportRepository mapper = session.getMapper(AirportRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Airport airport) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            airportRepository.update(airport);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            airportRepository.deleteById(id);
        }
    }
}
