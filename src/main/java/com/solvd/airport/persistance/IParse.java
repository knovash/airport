package com.solvd.airport.persistance;

import com.solvd.airport.persistance.Config;

import java.io.File;

public interface IParse {

    Config fileToObject(String fileName);

    void objectToFile(Config config, File file);
}
