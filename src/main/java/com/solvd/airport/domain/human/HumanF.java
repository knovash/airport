package com.solvd.airport.domain.human;

import com.solvd.airport.domain.listener.IEvent;

public abstract class HumanF implements IEvent {

    private Long id;
    private String name;

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
}
