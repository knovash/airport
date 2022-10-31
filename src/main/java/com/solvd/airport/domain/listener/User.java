package com.solvd.airport.domain.listener;

public class User implements IEvent {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void onEvent(EventType type) {
        System.out.println("On event " + type + " " + this.firstName);
    }
}
