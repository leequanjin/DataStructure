/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author leeda
 */
public class Organization extends Donee {

    public Organization(String id, String name) {
        super(id, name);
    }

    @Override
    public String getType() {
        return "Organization";
    }
}
