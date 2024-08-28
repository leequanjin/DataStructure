/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VolunteerSubsystem;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Asus
 */
public class VDAO {
        
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    // Create file if file not exist
    public static boolean chkFileExist(String filePath) {
        File file = new File(filePath);
        
        if(file.exists()){
            return true;
        } else{
            return false;
        }
    }
    
    public static boolean createFile(String filePath){
        File file = new File(filePath);
        
        try {
            file.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }   
}
