package com.solvd.airport.persistence;

import com.solvd.airport.domain.carrier.Pilot;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface PilotRepository {

    void create(@Param("pilot") Pilot pilot, @Param("aircarrierId") Long aircarrierId);

    List<Pilot> readAll();

    Optional<Pilot> readById(Long id);

    void update(@Param("pilot") Pilot pilot, @Param("aircarrierId") Long aircarrierId);

    void deleteById(Long id);
}
