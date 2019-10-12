package CustomerPackage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String personalID;
    private LocalDate membershipDate;
    private boolean isActiveMember;
    private List<GymVisit> gymVisits = new ArrayList<>();

    //Constructor
    public Customer(){}


    //GETTERS AND SETTERS BELOW
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public boolean isActiveMember() {
        return isActiveMember;
    }

    public void setActiveMember(boolean activeMember) {
        isActiveMember = activeMember;
    }

    public List<GymVisit> getGymVisits() {
        return gymVisits;
    }


    //Checks if the membership has expired and returns true if member should be active
    public boolean verifyIfActiveMember(){
        boolean isActive = false;
        LocalDate today = LocalDate.now();
        LocalDate expirationDate = this.membershipDate.plusYears(1);
        if (today.isBefore(expirationDate))
            isActive = true;
        return isActive;
    }

    //Returns all gym visits for the customer as a String
    public String showAllVisits(){
        String allVisits = "";
        for (GymVisit visit: this.gymVisits) {
            allVisits += visit + "\n";
        }
        return allVisits;
    }

    @Override
    public String toString() {

        String status;

        if (this.isActiveMember)
            status = "AKTIVT";
        else
            status = "UPPHÖRT";

        return "Namn: " + this.name + ", Personnummer: " + this.personalID +
                "\nSenaste årsbetalning: " + this.membershipDate + ", Medlemsskap: " + status + "\n";
    }

}
