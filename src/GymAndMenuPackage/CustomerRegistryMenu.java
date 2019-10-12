package GymAndMenuPackage;

import CustomerPackage.Customer;
import UtilityPackage.Utility;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomerRegistryMenu {

    public static List<JButton> jButtonList = new ArrayList<>();

    public static void customerRegistryMenu(BestGymEver bestGymEver){
        List<Customer> customerList = bestGymEver.getCustomerList();


        JPanel flowPanelButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel gridPanel = new JPanel(new GridLayout(5,1));
        gridPanel.setBackground(Color.BLACK);

        JLabel buttonHeader = new JLabel("Sortera Efter:", JLabel.CENTER);
        buttonHeader.setFont(new Font("Courier New", Font.BOLD, 14));
        buttonHeader.setForeground(Color.WHITE);
        gridPanel.add(buttonHeader);

        jButtonList.add(new JButton("Alfabetisk Ordning"));

        jButtonList.get(0).addActionListener(bestGymEver.windowMain);
        gridPanel.add(jButtonList.get(0));

        JButton membershipDateButton = new JButton("Registreringsdatum");
        gridPanel.add(membershipDateButton);

        JButton activeButton = new JButton("Visa Aktiva Medlemmar");
        gridPanel.add(activeButton);

        JButton notActiveButton = new JButton("Visa Inaktiva Medlemmar");
        gridPanel.add(notActiveButton);


        flowPanelButtons.setBounds(230,320, 190, 140);
        flowPanelButtons.setBackground(Color.BLACK);
        //flowPanelButtons.setOpaque(false);
        flowPanelButtons.add(gridPanel);

        //Showing the customer list as a scrollable window
        JPanel scrollableTextWindow = new JPanel();
        //Collections.sort(customerList, Comparator.comparing(Customer::getName));
        JScrollPane jScrollPane = Utility.getCustomerListInJScrollPane(bestGymEver, customerList);
        scrollableTextWindow.add(jScrollPane);
        scrollableTextWindow.setBounds(420, 190, 360, 412);
        scrollableTextWindow.setBackground(Color.BLACK);


        //Adding components to the layeredPane
        bestGymEver.jLayeredPane.add(flowPanelButtons, 1);
        //bestGymEver.windowMain.add(flowPanelLeading);
        bestGymEver.jLayeredPane.add(scrollableTextWindow, 2);
        //bestGymEver.windowMain.add(flowPanelCenter);


        Collections.sort(customerList, Comparator.comparing(Customer::getName));
        jScrollPane = Utility.getCustomerListInJScrollPane(bestGymEver, customerList);
        scrollableTextWindow.add(jScrollPane);
        scrollableTextWindow.revalidate();

        //Sorting the customer list alphabetically
        Collections.sort(customerList, Comparator.comparing(Customer::getName));
        jScrollPane = Utility.getCustomerListInJScrollPane(bestGymEver, customerList);
        scrollableTextWindow.add(jScrollPane);




        //MainMenu.mainMenu(bestGymEver);


    }
}

