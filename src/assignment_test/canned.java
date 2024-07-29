/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

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
public class canned extends food {
    
    private static final String FILE_PATH = "C:\\Users\\Asus\\Desktop\\DataStructure\\cannedFood.txt";
    private String id;
    
    public canned(){
    }
    
    public canned (String type, int qty, String note, Date expiryDate, int weight, String status){
        super(type, qty, note, expiryDate, weight, status);
        this.id = idGenerator();
    }
    
    public String idGenerator(){
        String filePath = FILE_PATH;
        int lineCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((br.readLine()) != null) {
                lineCount++;
            }
        } catch (IOException e) {
            
        }
        
        return "fc" + String.format("%03d", lineCount + 1); // food - canned food
    }

    public String getId() {
        return id;
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                String.format("\n%-15s %-2s %-7s" 
                        + "ID" + ":" + id);
    }
    
    public void saveFile(PrintWriter writer) {
        
        String expDate = dateToString(this.getExpiryDate());
        
        String details = String.format("(%s, %s, %d, %s, %s, %d, %s)",
            this.getId(), this.getType(), this.getQty(), this.getNote(), expDate, this.getWeight(), this.getStatus());
        
        writer.println(details);
    }
    
    public static String dateToString(Date date){
        // convert format: 30-07-2024
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        
        String dateString = formatter.format(date);
        return dateString;
    }
}
