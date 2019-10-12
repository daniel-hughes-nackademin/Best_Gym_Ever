package GymAndMenuPackage;

import CustomerPackage.Customer;
import UtilityPackage.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//The gym class. Each gym has a window, a list of customers and relevant file paths
public class BestGymEver {

    protected ActionListenerFrame windowMain = new ActionListenerFrame("Best Gym Ever - Seriously the best gym there is. Maybe.");
    JLayeredPane jLayeredPane = new JLayeredPane();
    private List<Customer> customerList;


    private final String filePathOriginalCustomers = "src\\fileFolder\\customers.txt";
    private final String filePathGymVisits = "src\\fileFolder\\Gym Visits.txt";
    private final String filePathUpdatedCustomers = "src\\fileFolder\\Customer List.txt";
    private final String filePathBackgroundImage = "src\\fileFolder\\Gym Picture.jpg";

    //gym constructor
    public BestGymEver() {

        ImagePanel imagePanel = new ImagePanel(new ImageIcon(filePathBackgroundImage).getImage());
        windowMain.add(jLayeredPane);
        //windowMain.getContentPane().add(panel);
        jLayeredPane.add(imagePanel, 0);
        windowMain.setPreferredSize(new Dimension(1200, 800));
        windowMain.pack();
        windowMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowMain.setResizable(false);
        windowMain.setVisible(true);
    }


    //SETTERS AND GETTERS BELOW
    public JFrame getWindowMain() {
        return windowMain;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
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
