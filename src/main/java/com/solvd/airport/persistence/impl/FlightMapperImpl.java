package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.flight.Flight;
import com.solvd.airport.persistence.MybatisConfig;
import com.solvd.airport.persistence.FlightRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class FlightMapperImpl implements FlightRepository {

    @Override
    public void create(Flight flight, Long aircarrierId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            FlightRepository mapper = session.getMapper(FlightRepository.class);
            mapper.create(flight, aircarrierId);
        }
    }

    @Override
    public List<Flight> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            FlightRepository mapper = session.getMapper(FlightRepository.class);
            return mapper.readAll();
        }
    }

    @Override
    public Optional<Flight> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            FlightRepository mapper = session.getMapper(FlightRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Flight flight, Long aircarrierId) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            FlightRepository mapper = session.getMapper(FlightRepository.class);
            mapper.update(flight, aircarrierId);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            FlightRepository mapper = session.getMapper(FlightRepository.class);
            mapper.deleteById(id);
        }
    }
}
