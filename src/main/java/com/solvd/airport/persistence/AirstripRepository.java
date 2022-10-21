package com.solvd.airport.persistence;

import com.solvd.airport.domain.port.Airstrip;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface AirstripRepository {

    void create(@Param("airstrip") Airstrip airstrip, @Param("airportId") Long airportId);

    List<Airstrip> readAll();

    Optional<Airstrip> readById(Long id);

    void update(@Param("airstrip") Airstrip airstrip, @Param("airportId") Long airportId);

    void deleteById(Long id);
}
