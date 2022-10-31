package com.solvd.airport.domain.carrier;

import com.solvd.airport.domain.human.HumanF;
import com.solvd.airport.domain.listener.EventType;

public class Pilot extends HumanF {

    private Long id;
    private String name;
    public String toString() {
        return ("Pilot: id: " + this.id + " name: " + this.name);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(EventType type) {

    }
}
