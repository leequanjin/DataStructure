/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoneeSubsystem;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Lee Quan Jin
 */
public abstract class Donee implements Serializable {

    private String id;
    private String name;
    private String location;
    final private LocalDate registrationDate;
    private String status;

    public Donee(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.registrationDate = LocalDate.now();
        this.status = "Active";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format(
                "%-15s |%-30s |%-15s |%-15s |%-20s |%s",
                id,
                name,
                getType(),
                location,
                registrationDate.toString(),
                status
        );
    }
}
