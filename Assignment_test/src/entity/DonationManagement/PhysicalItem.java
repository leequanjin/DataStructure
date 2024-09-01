/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.DonationManagement;


/**
 *
 * @author Heng Pei Lin
 */
public class PhysicalItem extends Item {
    private String note;
    
    public PhysicalItem(){}
    
    public PhysicalItem(String id, String donorID, String note){
        super(id, donorID);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    @Override
    public String getType() {
        return "Physical Item";
    }
    
    @Override
    public String toString(){
        if (note == null){
            note = " ";
        }
        return super.toString() + 
                String.format(" %-20s |", note);
    }
}
