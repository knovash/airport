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
        System.out.println("MAPPER create aircarrier");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository aircarrierRepository = session.getMapper(AircarrierRepository.class);
            aircarrierRepository.create(aircarrier);
        }
        System.out.println("MAPPER aircarrier created");
    }

    @Override
    public List<Aircarrier> readAll() {
        System.out.println("MAPPER readAll aircarriers");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository aircarrierRepository = session.getMapper(AircarrierRepository.class);
            return aircarrierRepository.readAll();
        }
    }

    @Override
    public Optional<Aircarrier> readById(Long id) {
        System.out.println("MAPPER readById aircarrier");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository mapper = session.getMapper(AircarrierRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Aircarrier aircarrier) {
        System.out.println("MAPPER update aircarrier");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository aircarrierRepository = session.getMapper(AircarrierRepository.class);
            aircarrierRepository.update(aircarrier);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("MAPPER deleteById aircarrier");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AircarrierRepository aircarrierRepository = session.getMapper(AircarrierRepository.class);
            aircarrierRepository.deleteById(id);
        }
    }
}
