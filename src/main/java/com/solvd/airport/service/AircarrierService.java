package com.solvd.airport.service;

import com.solvd.airport.domain.carrier.Aircarrier;
import com.solvd.airport.domain.port.Airstrip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AircarrierService {

    Aircarrier create(Aircarrier aircarrier);

    List<Aircarrier> readAll();

    Aircarrier readById(Long id);

    void update(Aircarrier aircarrier);

    void deleteById(Long id);
}
