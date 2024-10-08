/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.Donor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Kee Ke Shen
 */
public abstract class Donor implements Serializable {
    
    String id;
    String name;
    String category; // government, private, public
    final private LocalDate registrationDate;

    public Donor(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.registrationDate = LocalDate.now();
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
    
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public abstract String getType(); // Individual or Organization 

    @Override
    public String toString() {
        return String.format(
                "%-15s |%-30s |%-15s |%-15s |%s",
                id,
                name,
                getType(),
                getCategory(),
                registrationDate.toString()
        );
    }
}

