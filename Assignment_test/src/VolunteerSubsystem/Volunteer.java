package VolunteerSubsystem;

import java.io.Serializable;

// @author Clarist, Heng Pei Lin

public class Volunteer implements Serializable {
    
    private String volunteerID;
    private String name;
    private String gender;
    private int age;
    private String contactNo;
    
    public Volunteer(){}
    
    public Volunteer(String volunteerID, String name, String gender, int age, String contactNo) {
        this.volunteerID = volunteerID;
        this.name = name;
        this.gender = gender;
        this.age = age; 
        this.contactNo = contactNo;
    }

    
    public String getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age; 
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    
    @Override
    public String toString() {
        return String.format("| %-12s | %-30s | %-10s | %-5d | %-15s |", volunteerID, name, gender, age, contactNo);
    }
}