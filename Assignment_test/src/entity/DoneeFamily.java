/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Christopher Yap Jian Xing
 */

public class DoneeFamily extends Donee {
    
    public DoneeFamily(String id, String name, String location) {
        super(id, name, location);
    }

    @Override
    public String getType() {
        return "Family";
    }
    
}
