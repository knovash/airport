package com.solvd.airport.domain.passenger;

import com.solvd.airport.domain.human.HumanF;
import com.solvd.airport.domain.listener.EventType;

public class Passenger extends HumanF  {

    private Long id;
    private Passport passport;
    private String name;

    public String toString() {
        return ("Passenger: id: " + this.id + " name: " + this.name + "\n  passport: " + this.passport);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(EventType type) {
        System.out.println("On event " + type + " " + this.name);
    }
}
