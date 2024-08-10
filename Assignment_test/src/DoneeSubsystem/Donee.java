/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoneeSubsystem;

import java.io.Serializable;

/**
 *
 * @author Lee Quan Jin
 */
abstract class Donee implements Serializable {

    String id;
    String name;

    public Donee(String id, String name) {
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

    public abstract String getType(); // Individual or Organization

    @Override
    public String toString() {
        return "ID  : " + getId()
                + "\nName: " + getName()
                + "\nType: " + getType()
                + "\n";
    }
}
