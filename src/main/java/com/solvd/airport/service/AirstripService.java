package com.solvd.airport.service;

import com.solvd.airport.domain.port.Airstrip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AirstripService {

    Airstrip create(@Param("airstrip") Airstrip airstrip, @Param("airportId") Long airportId);

    List<Airstrip> readAll();

    Airstrip readById(Long id);

    void update(@Param("airstrip") Airstrip airstrip, @Param("airportId") Long airportId);

    void deleteById(Long id);
}
