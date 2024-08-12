/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class CannedFood extends Food {
    private String foodType;
    
    public CannedFood(){
    }
    
    public CannedFood(String id, int qty, String note, Date expiryDate, int weight, String status){
        super(id, qty, note, expiryDate, weight, status);
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
    
    @Override
    public String getType() {
        return "Canned Food";
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                String.format("\n%-15s %-2s %-20s" 
                        + "Food Type" + ":" + foodType);
    }
}
