/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonorSubsystem;

/**
 *
 * @author Kee Ke Shen
 */
public class Organization extends Donor {
    
    public Organization(String id, String name, String category){
        super(id, name, category);
    }
    
    @Override
    public String getType() {
        return "Organization";
    }
}
