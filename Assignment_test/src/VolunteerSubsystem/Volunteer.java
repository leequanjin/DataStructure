/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

/**
 *
 * @author Clarist Liew
 */
public class Volunteer {
    
    private String volunteerID;
    private String name;
    private int age;
    private String contactNo;

    // Constructor
    public Volunteer(String volunteerID, String name, int age, String contactNo) {
        this.volunteerID = volunteerID;
        this.name = name;
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
        return "Volunteer ID: " + volunteerID +
               "\nName: " + name +
               "\nAge: " + age +
               "\nContact No: " + contactNo;
    }
}

