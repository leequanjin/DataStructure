/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.Donee;

/**
 *
 * @author Christopher Yap Jian Xing
 */
public class DoneePeriodCount {

    private final String period;
    private final int individualCount;
    private final int familyCount;
    private final int organizationCount;

    public DoneePeriodCount(String period, int individualCount, int familyCount, int organizationCount) {
        this.period = period;
        this.individualCount = individualCount;
        this.familyCount = familyCount;
        this.organizationCount = organizationCount;
    }

    public String getPeriod() {
        return period;
    }

    public int getIndividualCount() {
        return individualCount;
    }

    public int getFamilyCount() {
        return familyCount;
    }

    public int getOrganizationCount() {
        return organizationCount;
    }

    @Override
    public String toString() {
        return String.format(
                "%-15s |%-20s |%-20s |%s",
                period,
                individualCount,
                familyCount,
                organizationCount);
    }
}
