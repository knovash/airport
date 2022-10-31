package com.solvd.airport.domain.carrier;

public class Aircraft {

    private Long id;
    private Integer number;
    private String model;

    public String toString() {
        return ("Aircraft: id: " + this.id + " number: " + this.number + " model: " + this.model);
    }

    public static AircraftBuilder builder() {
        return new AircraftBuilder(new Aircraft());
    }

    //for mutable
    public AircraftBuilder toBuilder() {
        return new AircraftBuilder(this);
    }

    public static class AircraftBuilder {

        private final Aircraft aircraft;

        public AircraftBuilder(Aircraft aircraft) {
            this.aircraft = aircraft;
        }

        public AircraftBuilder id(Long id) {
            this.aircraft.id = id;
            return this;
        }

        public AircraftBuilder number(Integer number) {
            this.aircraft.number = number;
            return this;
        }

        public AircraftBuilder model(String model) {
            this.aircraft.model = model;
            return this;
        }

        public Aircraft build() {
            return aircraft;
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }
}
