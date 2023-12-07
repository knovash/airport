package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PassengerMapperImpl implements PassengerRepository {

    @Override
    public void create(Passenger passenger) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.create(passenger);
        }
    }

    @Override
    public List<Passenger> readAll() {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            return passengerRepository.readAll();
        }
    }

    @Override
    public Optional<Passenger> readById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository mapper = session.getMapper(PassengerRepository.class);
            return mapper.readById(id);
        }
    }

    @Override
    public void update(Passenger passenger) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.update(passenger);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MybatisConfig.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.deleteById(id);
        }
    }
}
