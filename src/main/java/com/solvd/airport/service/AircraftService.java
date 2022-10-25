package com.solvd.airport.service;

import com.solvd.airport.domain.carrier.Aircraft;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AircraftService {

    Aircraft create(@Param("aircraft") Aircraft aircraft, @Param("aircarrierId") Long aircarrierId);

    List<Aircraft> readAll();

    Aircraft readById(Long id);

    void update(@Param("aircraft") Aircraft aircraft, @Param("aircarrierId") Long aircarrierId);

    void deleteById(Long id);
}
