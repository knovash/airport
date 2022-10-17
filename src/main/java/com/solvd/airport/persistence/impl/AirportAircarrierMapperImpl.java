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
        System.out.println("MAPPER create airportAircarrier");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            mapper.create(airportAircarrier);
        }
        System.out.println("MAPPER airportAircarrier created");
    }

    @Override
    public List<AirportAircarrier> readAll() {
        System.out.println("MAPPER readAll airportAircarriers");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            return mapper.readAll();
        }
    }

    @Override
    public Optional<AirportAircarrier> readById(Long id) {
        System.out.println("MAPPER readById airportAircarrier");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            return mapper.readById(id);
        }
    }

    //
    @Override
    public void update(AirportAircarrier airportAircarrier) {
        System.out.println("MAPPER update airportAircarrier");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            mapper.update(airportAircarrier);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("MAPPER deleteById airportAircarrier");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirportAircarrierRepository mapper = session.getMapper(AirportAircarrierRepository.class);
            mapper.deleteById(id);
        }
    }
}
