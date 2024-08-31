/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Christopher Yap Jian Xing
 */
public class Individual extends Donee {

    public Individual(String id, String name, String location) {
        super(id, name, location);
    }

    @Override
    public String getType() {
        return "Individual";
    }
}
