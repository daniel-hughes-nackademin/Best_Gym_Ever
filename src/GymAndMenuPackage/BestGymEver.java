package GymAndMenuPackage;

import CustomerPackage.Customer;
import UtilityPackage.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//The gym class. Each gym has a window, a list of customers and relevant file paths
public class BestGymEver {

    protected String title = "Best Gym Ever - Seriously the best gym there is. Maybe.";
    private List<Customer> customerList;


    private final String filePathOriginalCustomers = "src\\fileFolder\\customers.txt";
    private final String filePathGymVisits = "src\\fileFolder\\Gym Visits.txt";
    private final String filePathUpdatedCustomers = "src\\fileFolder\\Customer List.txt";
    private final String filePathBackgroundImage = "src\\fileFolder\\Gym Picture.jpg";

    //gym constructor
    public BestGymEver() {}

    public BestGymEver(String title) {
        this.title = title;
    }

    //SETTERS AND GETTERS BELOW
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePathBackgroundImage() {
        return filePathBackgroundImage;
    }

    public String getFilePathOriginalCustomers() {
        return filePathOriginalCustomers;
    }

    public String getFilePathGymVisits() {
        return filePathGymVisits;
    }

    public String getFilePathUpdatedCustomers() {
        return filePathUpdatedCustomers;
    }



}
