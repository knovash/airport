package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistence.MybatisConfig;
import com.solvd.airport.persistence.AirstripRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AirstripMapperImpl implements AirstripRepository {

    @Override
    public void create(Airstrip airstrip, Long airportID) {
        System.out.println("MAPPER create airstrip");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            airstripRepository.create(airstrip, airportID);
        }
    }

    @Override
    public List<Airstrip> readAll() {
        System.out.println("MAPPER readAll airstrips");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            return airstripRepository.readAll();
        }
    }

    @Override
    public Airstrip readById(Long id) {
        System.out.println("MAPPER readById airstrip");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            return airstripRepository.readById(id);
        }
    }
//
    @Override
    public void update(Airstrip airstrip, Long airportID) {
        System.out.println("MAPPER update airstrip");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            airstripRepository.update(airstrip, airportID);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("MAPPER deleteById airstrip");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            AirstripRepository airstripRepository = session.getMapper(AirstripRepository.class);
            airstripRepository.deleteById(id);
        }
    }
}
