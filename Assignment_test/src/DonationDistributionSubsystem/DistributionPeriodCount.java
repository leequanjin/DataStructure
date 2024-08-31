/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationDistributionSubsystem;

/**
 *
 * @author leeda
 */
public class DistributionPeriodCount {
    private final String period;
    private final int completedCount;
    private final int cancelledCount;
    private final int inProgressCount;

    public DistributionPeriodCount(String period, int completedCount, int cancelledCount, int inProgressCount) {
        this.period = period;
        this.completedCount = completedCount;
        this.cancelledCount = cancelledCount;
        this.inProgressCount = inProgressCount;
    }

    public String getPeriod() {
        return period;
    }

    public int getCompletedCount() {
        return completedCount;
    }

    public int getCancelledCount() {
        return cancelledCount;
    }

    public int getInProgressCount() {
        return inProgressCount;
    }
    
    @Override
    public String toString() {
        return String.format(
                "%-15s |%-20s |%-20s |%s",
                period,
                completedCount,
                cancelledCount,
                inProgressCount);
    }
}
