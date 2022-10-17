package com.solvd.airport.service.impl;

import com.solvd.airport.domain.exception.NotFound;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistence.PassportRepository;
import com.solvd.airport.persistence.impl.PassportMapperImpl;
import com.solvd.airport.service.PassportService;

import java.util.List;

public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    public PassportServiceImpl() {
        this.passportRepository = new PassportMapperImpl();
    }

    @Override
    public Passport create(Passport passport) {
        System.out.println("SERVICE create passport");
        passport.setId(null);
        passportRepository.create(passport);
        return passport;
    }

    @Override
    public List<Passport> readAll() {
        System.out.println("SERVICE readAll passports");
        return passportRepository.readAll();
    }

    @Override
    public Passport readById(Long id) {
        System.out.println("SERVICE readById passport");
        return passportRepository.readById(id)
                .orElseThrow(() -> new NotFound("Passport with id=" + id + " not found"));
    }

    @Override
    public void update(Passport passport) {
        System.out.println("SERVICE update passport");
        passportRepository.update(passport);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById passport");
        passportRepository.deleteById(id);
    }
}
