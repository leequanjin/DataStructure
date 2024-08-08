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
public class TinnedFood extends Food {
    
    private static final String FILE_PATH = "C:\\Users\\Asus\\Desktop\\DS_Assign\\DataStructure\\DonationListFile\\TinnedFood.txt";
    
    public TinnedFood(){
    }
    
    public TinnedFood(String id, int qty, String note, Date expiryDate, int weight, String status){
        super(id, qty, note, expiryDate, weight, status);
    }
    
    @Override
    public String getType() {
        return "Tinned Food";
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                String.format("\n%-15s %-2s %-7s" 
                        + "ID" + ":" + id);
    }
}
