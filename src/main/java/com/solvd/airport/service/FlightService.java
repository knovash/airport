package com.solvd.airport.service;

import com.solvd.airport.domain.flight.Flight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlightService {

    Flight create(@Param("flight") Flight flight, @Param("aircarrierId") Long aircarrierId);

    List<Flight> readAll();

    Flight readById(Long id);

    void update(@Param("flight") Flight flight, @Param("aircarrierId") Long aircarrierId);

    void deleteById(Long id);
}
