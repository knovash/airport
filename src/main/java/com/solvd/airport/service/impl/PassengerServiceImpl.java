package com.solvd.airport.service.impl;

import com.solvd.airport.domain.passenger.Passenger;
import com.solvd.airport.domain.passenger.Passport;
import com.solvd.airport.persistance.PassengerRepository;
import com.solvd.airport.persistance.impl.PassengerRepositoryImpl;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.PassportService;

import java.util.List;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassportService passportService;

    public PassengerServiceImpl() {
        this.passportService = new PassportServiceImpl();
        this.passengerRepository = new PassengerRepositoryImpl();
    }

//    private Long id;
//    private Passport passport;
//    private String name;

    @Override
    public Passenger create(Passenger passenger, Long ticketId) {
        passenger.setId(null);
        passengerRepository.create(passenger, ticketId);

        if (passenger.getPassport() != null) {
            Passport passport = passenger.getPassport();
            passportService.create(passport, passenger.getId());
            passenger.setPassport(passport);
        }

        return passenger;
    }

    @Override
    public List<Passenger> readAll() {
        return passengerRepository.readAll();
    }
}