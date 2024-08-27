package VolunteerSubsystem;
public class Volunteer {
    
    private String volunteerID;
    private String name;
    private String gender;
    private int age;
    private String contactNo;
    //private LinkedList<Event> assignedEvents; 
    

    
    public Volunteer(String volunteerID, String name, String gender, int age, String contactNo) {
        this.volunteerID = volunteerID;
        this.name = name;
        this.gender = gender;
        this.age = age; 
        this.contactNo = contactNo;
        //this.assignedEvents = new LinkedList<>();
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
    
//    public LinkedList<Event> getAssignedEvents() {
//        return assignedEvents;
//    }
//
//    public void assignEvent(Event event) {
//        this.assignedEvents.insert(event);
//    }

    
    @Override
    public String toString() {
        return "Volunteer ID: " + volunteerID +
               "\nName: " + name +
               "\nGender: " + gender +
               "\nAge: " + age +
               "\nContact No: " + contactNo;
    }
}