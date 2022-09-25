package com.solvd.airport.service;

import com.solvd.airport.domain.passenger.Passport;

import java.util.List;

public interface PassportService {

    Passport create(Passport passport);

    List<Passport> readAll();

}