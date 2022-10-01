package com.solvd.airport.service.impl;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.impl.PassengerMapperImpl;
import com.solvd.airport.persistence.impl.PassengerRepositoryImpl;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.PassportService;

import java.util.List;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassportService passportService;

    public PassengerServiceImpl() {
        this.passportService = new PassportServiceImpl();
//        this.passengerRepository = new PassengerRepositoryImpl();
        this.passengerRepository = new PassengerMapperImpl();
    }

    @Override
    public Passenger create(Passenger passenger) {
        System.out.println("SERVICE create passenger");
        passenger.setId(null);
        if (passenger.getPassport() != null) {
            Passport passport = passportService.create(passenger.getPassport());
            passenger.setPassport(passport);
        }
        passengerRepository.create(passenger);
        return passenger;
    }

    @Override
    public List<Passenger> readAll() {
        System.out.println("SERVICE readAll passengers");
        return passengerRepository.readAll();
    }

    @Override
    public Passenger readById(Long id) {
        System.out.println("SERVICE readById passenger");
        return passengerRepository.readById(id);
    }

    @Override
    public void update(Passenger passenger) {
        System.out.println("SERVICE update passenger");
        passengerRepository.update(passenger);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("SERVICE deleteById passenger");
        passengerRepository.deleteById(id);
    }
}
