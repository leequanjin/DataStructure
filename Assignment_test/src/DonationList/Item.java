/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Asus
 */
public class Item {
    private String type;
    private int qty;
    private String note;
    
    public Item(){}
    
    public Item(String type, int qty, String note){
        this.type = type;
        this.qty = qty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getQty(){
        return qty;
    }
    
    public void setQty(int qty){
        this.qty = qty;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    @Override
    public String toString(){
        return String.format("\n%-15s %-2s %-20s \n%-15s %-2s %-5d \n%-15s %-2s %-100s" 
                + "Type" + ":" + type
                + "Quantity" + ":" + qty
                + "Remarks" + ":" + note);
    }
}
