package com.solvd.airport.service.impl;


import com.solvd.airport.domain.port.Airstrip;
import com.solvd.airport.persistance.AirstripRepository;
import com.solvd.airport.persistance.impl.AirstripRepositoryImpl;
import com.solvd.airport.service.AirstripService;


import java.util.List;

public class AirstripServiceImpl implements AirstripService {

    private final AirstripRepository airstripRepository;

    public AirstripServiceImpl() {
        this.airstripRepository = new AirstripRepositoryImpl();
    }

//    private Long id;
//    private Integer number;
//    private Long airportId;

    @Override
    public Airstrip create(Airstrip airstrip, Long airportId) {
        airstrip.setId(null);
        airstripRepository.create(airstrip, airportId);
        return airstrip;
    }

    @Override
    public List<Airstrip> readAll() {
        return airstripRepository.readAll();
    }
}