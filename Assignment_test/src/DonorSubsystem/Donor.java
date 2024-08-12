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
    String category; // government, private, public

    public Donor(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
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
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public abstract String getType(); // Individual or Organization 

    @Override
    public String toString() {
        return "Donor{" +
                "Id : '" + id + '\'' +
                ", Name : '" + name.toUpperCase() + '\'' +
                ", Category : '" + category.toUpperCase() + '\'' +
                '}';
    }
}

