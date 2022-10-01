package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PassengerMapperImpl implements PassengerRepository {

    @Override
    public void create(Passenger passenger) {
        System.out.println("MAPPER create passenger");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.create(passenger);
        }
    }

    @Override
    public List<Passenger> readAll() {
        System.out.println("MAPPER readAll passengers");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            return passengerRepository.readAll();
        }
    }

    @Override
    public Passenger readById(Long id) {
        System.out.println("MAPPER readById passenger");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            return passengerRepository.readById(id);
        }
    }
//
    @Override
    public void update(Passenger passenger) {
        System.out.println("MAPPER update passenger");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.update(passenger);
        }
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("MAPPER deleteById passenger");
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.deleteById(id);
        }
    }
}
