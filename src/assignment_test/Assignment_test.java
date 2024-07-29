/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment_test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Assignment_test {

    /**
     * @param args the command line arguments
     */
    
    private static final String CAN_FILE = "C:\\Users\\Asus\\Desktop\\DataStructure\\cannedFood.txt";
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        // -- Define all the file --
        // file: to store canned food
        String cannedFile = CAN_FILE;
        //if file does not exist, create a file
        chkFileExist(cannedFile);
        
        
        
    }
    
    //for files
    // Create file if file not exist
    public static void chkFileExist(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                //System.out.println("File created: " + filePath);
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Error creating file: " + e.getMessage() + ANSI_RESET);
        }
    }
    
}
