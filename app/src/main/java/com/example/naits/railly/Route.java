package com.example.naits.railly;

/**
 * Created by naits on 19/12/2016.
 */

public class Route {
    private String departure, arrival;
    private int id;

    public Route(int id, String departure, String arrival){
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public int getId() {
        return id;
    }
}
