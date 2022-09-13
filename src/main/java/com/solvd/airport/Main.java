package com.solvd.airport;

import com.solvd.airport.port.AirPort;

public class Main {
    public static void main(String[] args) {
        AirPort airPort = new AirPort();
        System.out.println(airPort.getAirPortName());
    }
}