/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonorSubsystem;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public abstract class Donor implements Serializable {
    
    String id;
    String name;

    public Donor(String id, String name) {
        this.id = id;
        this.name = name;
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

    public abstract String getType(); // Individual or Organization or Family

    @Override
    public String toString() {
        return "ID  : " + getId()
                + "\nName: " + getName()
                + "\nType: " + getType();
    }
}
