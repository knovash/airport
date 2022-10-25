package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistence.PassportRepository;
import com.solvd.airport.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PassportMapperImpl implements PassportRepository {

    @Override
    public void create(Passport passport) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            passportRepository.create(passport);
        }
    }

    @Override
    public List<Passport> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            return passportRepository.readAll();
        }
    }

    @Override
    public Optional<Passport> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository mapper = session.getMapper(PassportRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Passport passport) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            passportRepository.update(passport);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            passportRepository.deleteById(id);
        }
    }
}
