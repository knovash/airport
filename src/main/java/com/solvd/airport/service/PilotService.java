package com.solvd.airport.service;

import com.solvd.airport.domain.carrier.Pilot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PilotService {

    Pilot create(@Param("pilot") Pilot pilot, @Param("aircarrierId") Long aircarrierId);

    List<Pilot> readAll();

    Pilot readById(Long id);

    void update(@Param("pilot") Pilot pilot, @Param("aircarrierId") Long aircarrierId);

    void deleteById(Long id);
}
