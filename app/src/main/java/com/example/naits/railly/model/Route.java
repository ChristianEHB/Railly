package com.example.naits.railly.model;

/**
 * Created by naits on 19/12/2016.
 */

public class Route {
    private String departure, arrival;
    private int id;

    private String arrivalTime, destination, platform, canceled, delay;



    public Route(int id, String departure, String arrival){
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Route(int id, String arrivalTime, String destination, String platform, String canceled, String delay){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.destination = destination;
        this.platform = platform;
        this.canceled = canceled;
        this.delay = delay;
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

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getCanceled() {
        return canceled;
    }

    public String getDestination() {
        return destination;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDelay() {
        return delay;
    }


    public void setDelay(String delay) {
        this.delay = delay;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }
}
