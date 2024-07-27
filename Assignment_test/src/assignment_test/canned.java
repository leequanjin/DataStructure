/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class canned extends food {
    private String type;
    
    public canned(){
    }
    
    public canned(int qty, Date expiryDate, int weight, String type){
        super(qty, expiryDate, weight);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                String.format("\n%-15s %-2s %-30s \n%-15s %-2s %-20d" 
                        + "Canned Type" + ":" + type);
    }
    
}
