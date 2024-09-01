
package entity.Event;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event implements Serializable {
    private String eventID;
    private String eventName;
    private Date date;
    private String time;
    private String location;

    

    
    public Event(String eventID, String eventName, Date date, String time,  String location ) {
        this.eventID= eventID;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.location = location;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    


    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(date);
    return "Event Name: " + eventName + '\n' + "Date: " + dateString + '\n' + "Time: " + time + '\n' + "Location: " + location ;
    }
}    