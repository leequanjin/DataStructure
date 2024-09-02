/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Heng Pei Lin
 */
public class DAO {
    
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