/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_test;

/**
 *
 * @author Lee Quan Jin
 */
public class TestDonation {
    private String donationId;
    private double amount;
    private String donationType; // cash or kind

    public TestDonation(String donationId, double amount, String donationType) {
        this.donationId = donationId;
        this.amount = amount;
        this.donationType = donationType;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    @Override
    public String toString() {
        return "Donation{" + "donationId=" + donationId + ", amount=" + amount + ", donationType=" + donationType + '}';
    }
}
