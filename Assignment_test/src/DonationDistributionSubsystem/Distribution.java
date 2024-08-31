/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DonationDistributionSubsystem;

import CommonResources.LinkedList;
import DonationList.Item;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author leeda
 */
class Distribution implements Serializable {

    private String id;
    private String doneeId;
    private String doneeName;
    private ManageItems<Item> donations;
    final private LocalDate distibutionDate;
    private String status;

    public Distribution(String id, String doneeId, String doneeName, ManageItems donations) {
        this.id = id;
        this.doneeId = doneeId;
        this.doneeName = doneeName;
        this.donations = donations;
        this.distibutionDate = LocalDate.now();
        this.status = "In Progress";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoneeId() {
        return doneeId;
    }

    public void setDoneeId(String doneeId) {
        this.doneeId = doneeId;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    public LinkedList<Item> getDonations() {
        return donations;
    }

    public void setDonations(ManageItems<Item> donations) {
        this.donations = donations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
       
        return String.format(
                "%-20s |%-20s |%-30s |%-20s |%-20s |%s\n",
                id,
                doneeId,
                doneeName,
                distibutionDate,
                status,
                donations.toStringDonations()
        );
    }
}
