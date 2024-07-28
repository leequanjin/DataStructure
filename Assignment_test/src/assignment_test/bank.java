/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Asus
 */
public class bank extends money {
    private String bankType;
    
    public bank(){
    }
    
    public bank(double amount, String bankType){
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
