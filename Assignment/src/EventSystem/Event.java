
package EventSystem;

public class Event {
    private String name;
    private String date;
    private String time;
    private String location;
    private String ticket;
    private String sponsorship;
    

    
    public Event(String time, String date, String location, String name, String sponsorship, String ticket) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.ticket = ticket;
        this.sponsorship = sponsorship;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getSponsorship() {
        return sponsorship;
    }

    public void setSponsorship(String sponsorship) {
        this.sponsorship = sponsorship;
    }

    @Override
    public String toString() {
    return "Name: " + name + '\n' + "Date: " + date + '\n' + "Time: " + time + '\n' + "Location: " + location + '\n' +"Ticket: " + ticket + '\n' + "sponsorship: " + sponsorship;
    }
}    


