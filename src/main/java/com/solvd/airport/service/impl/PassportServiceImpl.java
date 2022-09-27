package com.solvd.airport.service.impl;

import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.PassportRepository;
import com.solvd.airport.persistance.impl.PassportRepositoryImpl;
import com.solvd.airport.service.PassportService;

import java.util.List;

public class PassportServiceImpl implements PassportService {

    private PassportRepository passportRepository;

    public PassportServiceImpl() {
        this.passportRepository = new PassportRepositoryImpl();
    }

//    private Long id;
//    private Integer number;


    @Override
    public Passport create(Passport passport, Long passengerId) {
        passport.setId(null);
        passportRepository.create(passport, passengerId);
        return passport;
    }

    @Override
    public List<Passport> readAll() {
        return passportRepository.readAll();
    }
}
