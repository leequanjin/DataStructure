/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

import java.io.Serializable;

/**
 *
 * @author Heng Pei Lin
 */
public abstract class Item implements Serializable {
    String id;
    
    public Item(){}
    
    public Item(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public abstract String getType();
    
    @Override
    public String toString() {
        return  "ID:" + id + 
                "\nType:" + getType();
    }
}
