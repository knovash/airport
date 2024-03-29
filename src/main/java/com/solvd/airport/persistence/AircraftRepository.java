package com.solvd.airport.persistence;

import com.solvd.airport.domain.carrier.Aircraft;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface AircraftRepository {

    void create(@Param("aircraft") Aircraft aircraft, @Param("aircarrierId") Long aircarrierId);

    List<Aircraft> readAll();

    Optional<Aircraft> readById(Long id);

    void update(@Param("aircraft") Aircraft aircraft, @Param("aircarrierId") Long aircarrierId);

    void deleteById(Long id);
}
