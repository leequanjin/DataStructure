/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

import java.io.Serializable;

/**
 *
 * @author Heng Pei Lin
 */
public class EventVolunteer implements Serializable{
    private String eventID;
    private String volunteerID;
    
    public EventVolunteer(){}
    
    public EventVolunteer(String eventID,String volunteerID){
        this.eventID = eventID;
        this.volunteerID = volunteerID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }
    
    public String toString(){
        return String.format("| %-12s | %-12s |", eventID, volunteerID);
    }
}
