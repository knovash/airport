package com.solvd.airport.persistence;

import com.solvd.airport.domain.flight.Flight;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    void create(@Param("flight") Flight flight, @Param("aircarrierId") Long aircarrierId);

    List<Flight> readAll();

    Optional<Flight> readById(Long id);

    void update(@Param("flight") Flight flight, @Param("aircarrierId") Long aircarrierId);

    void deleteById(Long id);
}
