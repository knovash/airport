package com.solvd.airport.service;

import com.solvd.airport.persistance.Settings;

import java.io.File;

public interface IParse {

    Settings fileToObject(String fileName);

    void objectToFile(Settings settings, File file);
}
