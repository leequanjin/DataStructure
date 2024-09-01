/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.Donor;

/**
 *
 * @author kee ke shen
 */
public class DonorPeriodCount {

    private final String period;
    private final int individualCount;
    private final int organizationCount;

    public DonorPeriodCount(String period, int individualCount, int organizationCount) {
        this.period = period;
        this.individualCount = individualCount;
        this.organizationCount = organizationCount;
    }

    public String getPeriod() {
        return period;
    }

    public int getIndividualCount() {
        return individualCount;
    }


    public int getOrganizationCount() {
        return organizationCount;
    }

    @Override
    public String toString() {
        return String.format(
                "%-15s |%-20s |%s",
                period,
                individualCount,
                organizationCount);
    }
}
