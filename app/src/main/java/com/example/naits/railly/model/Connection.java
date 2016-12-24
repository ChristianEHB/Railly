package com.example.naits.railly.model;

import java.util.List;

import com.example.naits.railly.util.DateUtil;

public class Connection {
    private int id;
    private int duration;
    private Departure departure, arrival;
    private List<Via> vias;

    public Connection(int id, int duration, Departure departure, Departure arrival, List<Via> vias) {
        super();
        this.id = id;
        this.duration = duration;
        this.departure = departure;
        this.arrival = arrival;
        this.vias = vias;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public Departure getDeparture() {
        return departure;
    }

    public Departure getArrival() {
        return arrival;
    }

    public List<Via> getVias() {
        return vias;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public void setArrival(Departure arrival) {
        this.arrival = arrival;
    }

    public void setVias(List<Via> vias) {
        this.vias = vias;
    }

    public float getDurationInMinutes() {
        return duration / 60f;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
        result = prime * result + ((departure == null) ? 0 : departure.hashCode());
        result = prime * result + duration;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Connection other = (Connection) obj;
        if (arrival == null) {
            if (other.arrival != null) return false;
        } else if (!arrival.equals(other.arrival)) return false;
        if (departure == null) {
            if (other.departure != null) return false;
        } else if (!departure.equals(other.departure)) return false;
        if (duration != other.duration) return false;
        if (id != other.id) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection from " + departure.getToStation().getName() + " to " + arrival.getToStation().getName());
        sb.append("\n-Departure at " + DateUtil.timeStampToDate(departure.getDepartureInfo().getTimeStamp()) + " arrival at " + DateUtil.timeStampToDate(arrival.getDepartureInfo().getTimeStamp()) + " takes " + getDurationInMinutes() + " min");
        if (vias.size() != 0) {
            sb.append("\n\tVia: ");
            for (Via v : vias)
                sb.append("\n" + v.toString());
        }
        return sb.toString();
    }
}
