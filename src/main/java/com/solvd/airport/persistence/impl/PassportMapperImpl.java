package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistence.PassportRepository;
import com.solvd.airport.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PassportMapperImpl implements PassportRepository {

    @Override
    public void create(Passport passport) {
        System.out.println("MAPPER create passport");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            passportRepository.create(passport);
        }
    }

    @Override
    public List<Passport> readAll() {
        System.out.println("MAPPER readAll passports");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            return passportRepository.readAll();
        }
    }

    @Override
    public Passport readById(Long id) {
        System.out.println("MAPPER readById passport");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            return passportRepository.readById(id);
        }
    }

    @Override
    public void update(Passport passport) {
        System.out.println("MAPPER update passport");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            passportRepository.update(passport);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("MAPPER deleteById passport");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassportRepository passportRepository = session.getMapper(PassportRepository.class);
            passportRepository.deleteById(id);
        }
    }
}
