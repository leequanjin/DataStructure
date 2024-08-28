/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoneeSubsystem;

/**
 *
 * @author Lee Quan Jin
 */

public class Family extends Donee {
    
    public Family(String id, String name, String location) {
        super(id, name, location);
    }

    @Override
    public String getType() {
        return "Family";
    }
    
}
