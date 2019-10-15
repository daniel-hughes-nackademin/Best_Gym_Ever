package UtilityPackage;

import CustomerPackage.Customer;
import GymAndMenuPackage.BestGymEver;
import GymAndMenuPackage.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Window extends JFrame {

    protected JLayeredPane jLayeredPane;
    JPanel scrollableTextWindow;
    BestGymEver bestGymEver;
    /*JButton alphaButton;
    JButton personalID_Button;
    JButton membershipButton;
    JButton activeButton;
    JButton notActiveButton;
    JButton mainMenuButton;*/


    //List<JButton> jButtonList;

    public Window(BestGymEver bestGymEver){
        super(bestGymEver.getTitle());
        this.bestGymEver = bestGymEver;
        ImagePanel imagePanel = new ImagePanel(new ImageIcon(bestGymEver.getFilePathBackgroundImage()).getImage());
        jLayeredPane = new JLayeredPane();
        this.add(jLayeredPane);
        jLayeredPane.add(imagePanel, 0);

        this.setPreferredSize(new Dimension(1200, 800));
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initializeRegistryMenu(){
        JPanel flowPanelButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
        flowPanelButtons.setBounds(230,320, 190, 170);
        flowPanelButtons.setBackground(Color.BLACK);

        flowPanelButtons.setVisible(true);
        JPanel gridPanel = new JPanel(new GridLayout(0,1));
        gridPanel.setBackground(Color.BLACK);
        flowPanelButtons.add(gridPanel);

        //Adding label over buttons
        JLabel buttonHeader = new JLabel("Sortera Efter:", JLabel.CENTER);
        buttonHeader.setFont(new Font("Courier New", Font.BOLD, 14));
        buttonHeader.setForeground(Color.WHITE);
        gridPanel.add(buttonHeader);

        //Empty label for formatting the buttons to the correct positions
        JLabel emptyLabel = new JLabel("", JLabel.CENTER);
        gridPanel.add(emptyLabel);

        //Button 1
        JButton alphaButton = new JButton("Alfabetisk Ordning");
        alphaButton.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            Collections.sort(bestGymEver.getCustomerList(), Comparator.comparing(Customer::getName)); //sort alphabetically
            makeCustomerListInScrollableWindow(bestGymEver.getCustomerList());
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(alphaButton);
        gridPanel.add(emptyLabel);

        //Button 2
        JButton personalID_Button = new JButton("Personnummer");
        personalID_Button.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            Collections.sort(bestGymEver.getCustomerList(), Comparator.comparing(Customer::getPersonalID)); //sort by personal ID
            makeCustomerListInScrollableWindow(bestGymEver.getCustomerList());
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(personalID_Button);
        gridPanel.add(emptyLabel);

        //Button 3
        JButton membershipButton = new JButton("Registreringsdatum");
        membershipButton.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            Collections.sort(bestGymEver.getCustomerList(), Comparator.comparing(Customer::getMembershipDate)); //sort by membership date
            makeCustomerListInScrollableWindow(bestGymEver.getCustomerList());
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(membershipButton);
        gridPanel.add(emptyLabel);

        //Button 4
        JButton activeButton = new JButton("Visa Aktiva Medlemmar");
        activeButton.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            List<Customer> activeMembers = new ArrayList<>();
            for (Customer customer: bestGymEver.getCustomerList()) {
                if (customer.isActiveMember()){
                    activeMembers.add(customer);
                }
            }
            Collections.sort(activeMembers, Comparator.comparing(Customer::getName));
            makeCustomerListInScrollableWindow(activeMembers);
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(activeButton);
        gridPanel.add(emptyLabel);

        //Button 5
        JButton notActiveButton = new JButton("Visa Inaktiva Medlemmar");
        notActiveButton.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            List<Customer> activeMembers = new ArrayList<>();
            for (Customer customer: bestGymEver.getCustomerList()) {
                if (!customer.isActiveMember()){
                    activeMembers.add(customer);
                }
            }
            Collections.sort(activeMembers, Comparator.comparing(Customer::getName));
            makeCustomerListInScrollableWindow(activeMembers);
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(notActiveButton);
        gridPanel.add(emptyLabel);

        //Adding side button panel to the layeredPane
        this.jLayeredPane.add(flowPanelButtons, 1);

        //Main menu button
        JButton mainMenuButton = new JButton("Huvudmeny");
        mainMenuButton.addActionListener(e -> {
            this.removeLayeredComponents();
            MainMenu.mainMenu(bestGymEver);
        });
        mainMenuButton.setBounds(535, 620, 120, 50);
        this.jLayeredPane.add(mainMenuButton,1);


        //Adding scrollable window with customer list to layeredPane
        makeCustomerListInScrollableWindow(bestGymEver.getCustomerList());
        jLayeredPane.add(scrollableTextWindow, 2);
    }

    private void makeCustomerListInScrollableWindow(List<Customer> customerList){
        //Showing the customer list as a scrollable window
        scrollableTextWindow = new JPanel();
        JScrollPane jScrollPane = Utility.getCustomerListInJScrollPane(customerList);
        scrollableTextWindow.add(jScrollPane);
        scrollableTextWindow.setBounds(420, 190, 360, 412);
        scrollableTextWindow.setBackground(Color.BLACK);
    }

    public void removeLayeredComponents(){
        this.jLayeredPane.removeAll();
        this.jLayeredPane.revalidate();
        this.jLayeredPane.repaint();
        ImagePanel imagePanel = new ImagePanel(new ImageIcon(bestGymEver.getFilePathBackgroundImage()).getImage());
        this.jLayeredPane.add(imagePanel, 0);
    }
}
