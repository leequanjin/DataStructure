/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Asus
 */
public class PhysicalItem extends Item {
    private int qty;
    private String note;
    
    public PhysicalItem(){}
    
    public PhysicalItem(String id, int qty, String note){
        super(id);
        this.qty = qty;
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
    public String getType() {
        return "Physical Item";
    }
    
    @Override
    public String toString(){
        return super.toString() + 
                String.format("\n%-15s %-2s %-5d \n%-15s %-2s %-100s" 
                + "Quantity" + ":" + qty
                + "Remarks" + ":" + note);
    }
}
