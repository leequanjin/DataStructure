/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoneeSubsystem;

/**
 *
 * @author Lee Quan Jin
 */
public class Individual extends Donee {

    public Individual(String id, String name) {
        super(id, name);
    }

    @Override
    public String getType() {
        return "Individual";
    }
}
