package GymAndMenuPackage;

import CustomerPackage.Customer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//The gym class. Each gym has a window, a list of customers and relevant file paths
public class BestGymEver {

    private String title = "Best Gym Ever - Seriously the best gym there is. Maybe.";
    private List<Customer> customerList;


    private final Path pathOriginalCustomers = Paths.get("src\\fileFolder\\customers.txt");
    private final Path pathGymVisits = Paths.get("src\\fileFolder\\Gym Visits.txt");
    private final Path pathUpdatedCustomers = Paths.get("src\\fileFolder\\Customer List.txt");
    private final Path pathBackgroundImage = Paths.get("src\\fileFolder\\Gym Picture.jpg");

    //gym constructor
    BestGymEver() {}

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

    public Path getPathBackgroundImage() {
        return pathBackgroundImage;
    }

    public Path getPathOriginalCustomers() {
        return pathOriginalCustomers;
    }

    Path getPathGymVisits() {
        return pathGymVisits;
    }

    public Path getPathUpdatedCustomers() {
        return pathUpdatedCustomers;
    }



}
