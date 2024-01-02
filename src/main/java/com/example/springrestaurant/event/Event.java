package com.example.springrestaurant.event;

import com.example.springrestaurant.client.Client;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvent;
    private String eventName;
    private int guests;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client organizer;

    public Event() {
    }

    public Event(int idEvent, String eventName, int guests, Date date, Client organizer) {
        this.idEvent = idEvent;
        this.eventName = eventName;
        this.guests = guests;
        this.date = date;
        this.organizer = organizer;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Client organizer) {
        this.organizer = organizer;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", eventName='" + eventName + '\'' +
                ", guests=" + guests +
                ", date=" + date +
                ", organizer=" + organizer +
                '}';
    }
}
