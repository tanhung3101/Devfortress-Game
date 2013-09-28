/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.model;

/**
 *
 * @author Nop
 */
public class Event {
    private String eventID;
    private String eventName;
    private double eventChance;
    private String eventEffect;
    private String eventStatus;

    public Event(String eventID, String eventName, double eventChance, String eventEffect, String eventStatus) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventChance = eventChance;
        this.eventEffect = eventEffect;
        this.eventStatus = eventStatus;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public double getEventChance() {
        return eventChance;
    }

    public void setEventChance(double eventChance) {
        this.eventChance = eventChance;
    }

    public String getEventEffect() {
        return eventEffect;
    }

    public void setEventEffect(String eventEffect) {
        this.eventEffect = eventEffect;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }
    
    
    
}
