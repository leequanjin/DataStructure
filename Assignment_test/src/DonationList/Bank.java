/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationList;

/**
 *
 * @author Asus
 */
public class Bank extends Money {
    private String bankType;
    
    public Bank(){
    }
    
    public Bank(double amount, String bankType){
        super(amount);
        this.bankType = bankType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
}
