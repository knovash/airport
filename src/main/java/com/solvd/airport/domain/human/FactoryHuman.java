package com.solvd.airport.domain.human;

import com.solvd.airport.domain.carrier.Pilot;
import com.solvd.airport.domain.passenger.Passenger;

public class FactoryHuman {

    public static HumanF getHuman(HumanType type){
        HumanF human = null;
        switch (type) {
            case PASSENGER:
                human = new Passenger();
                human.setName("NewPassengerName" + (int) (Math.random() * 333));
                break;
            case PILOT:
                human = new Pilot();
                human.setName("NewPilotName" + (int) (Math.random() * 333));
                break;
            default:
                break;
        }
        return human;
    }
}
