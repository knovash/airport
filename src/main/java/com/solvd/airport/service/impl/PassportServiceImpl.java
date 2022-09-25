package com.solvd.airport.service.impl;

import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.PassportRepository;
import com.solvd.airport.persistance.impl.PassportRepositoryImpl;
import com.solvd.airport.service.PassportService;

import java.util.List;

public class PassportServiceImpl implements PassportService {

    private PassportRepository passportRepository = new PassportRepositoryImpl();

    public PassportServiceImpl() {
        this.passportRepository = new PassportRepositoryImpl();

    }

    @Override
    public Passport create(Passport passport) {
        passport.setId(null);
        passportRepository.create(passport); // passportRepository в persistance там sql insert зааносит информацию из полей в бд
        return passport;
    }

    @Override
    public List<Passport> readAll() {
        return null;
    }
}
