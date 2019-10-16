package UtilityPackage;

import CustomerPackage.Customer;
import GymAndMenuPackage.BestGymEver;
import GymAndMenuPackage.MainMenu;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Window extends JFrame {

    private JLayeredPane jLayeredPane;
    private JPanel scrollableTextWindow;
    private BestGymEver bestGymEver;


    public Window(BestGymEver bestGymEver){
        super(bestGymEver.getTitle());
        this.bestGymEver = bestGymEver;
        ImagePanel imagePanel = new ImagePanel(new ImageIcon(String.valueOf(bestGymEver.getPathBackgroundImage())).getImage());
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

        //Arrow button to change order of the list
        String arrowUp = "\u2191";
        String arrowDown = "\u2193";
        JButton reverseListButton = new JButton(arrowDown);
        reverseListButton.setFont(new Font("Courier New", Font.BOLD, 24));
        reverseListButton.addActionListener(e -> {
            if (reverseListButton.getText().equals(arrowDown)){
                reverseListButton.setText(arrowUp);
            }
            else if (reverseListButton.getText().equals(arrowUp)){
                reverseListButton.setText(arrowDown);
            }
            Collections.reverse(bestGymEver.getCustomerList());
            jLayeredPane.remove(scrollableTextWindow);
            makeCustomerListInScrollableWindow(bestGymEver.getCustomerList());
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        reverseListButton.setBounds(370, 270, 50, 50);
        this.jLayeredPane.add(reverseListButton,1);



        //Making flowpanel for button arrangement
        JPanel flowPanelButtons = new JPanel(new FlowLayout(FlowLayout.LEADING));
        flowPanelButtons.setBounds(230,320, 190, 200);
        flowPanelButtons.setBackground(Color.BLACK);

        //Gridpanel for buttons, add to flowpanel
        JPanel gridPanel = new JPanel(new GridLayout(0,1));
        gridPanel.setBounds(230,320, 190, 200);
        gridPanel.setBackground(Color.BLACK);
        flowPanelButtons.add(gridPanel);

        //Adding label over buttons
        JLabel buttonHeader = new JLabel("Sortera Efter:", JLabel.CENTER);
        buttonHeader.setFont(new Font("Courier New", Font.BOLD, 14));
        //buttonHeader.setForeground(Color.WHITE);
        gridPanel.add(buttonHeader);

        //Empty label for formatting the buttons to the correct positions
        JLabel emptyLabel = new JLabel("", JLabel.CENTER);
        gridPanel.add(emptyLabel);

        //Button 1
        JButton firstNameButton = new JButton("Förnamn");
        firstNameButton.addActionListener(e -> {
            bestGymEver.setCustomerList(Utility.getCustomerListFromFile(bestGymEver.getPathUpdatedCustomers()));
            jLayeredPane.remove(scrollableTextWindow);
            bestGymEver.getCustomerList().sort(Comparator.comparing(Customer::getName)); //sort alphabetically
            if (reverseListButton.getText().equals(arrowUp))
                Collections.reverse(bestGymEver.getCustomerList());
            makeCustomerListInScrollableWindow(bestGymEver.getCustomerList());
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(firstNameButton);
        gridPanel.add(emptyLabel);

        //Button 2
       JButton lastNameButton = new JButton("Efternamn");
        lastNameButton.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            bestGymEver.setCustomerList(Utility.getCustomerListFromFile(bestGymEver.getPathUpdatedCustomers()));
            List<Customer> lastNameList = bestGymEver.getCustomerList();
             //Sort alphabetically by last name, then first name
            lastNameList.sort(Comparator.comparing((Customer c) ->
                    c.getName().substring(c.getName().indexOf(' ') + 1)).thenComparing(c -> c.getName().substring(0, c.getName().indexOf(' '))));
            //Formats names in list to "Last, First" and displays in scrollPanel
            Utility.switchFirstAndLastNamesInCustomerList(lastNameList);
            if (reverseListButton.getText().equals(arrowUp))
                Collections.reverse(lastNameList);
            makeCustomerListInScrollableWindow(lastNameList);
            this.jLayeredPane.add(scrollableTextWindow, 2);
            bestGymEver.setCustomerList(lastNameList);
        });
        gridPanel.add(lastNameButton);
        gridPanel.add(emptyLabel);

        //Button 3
        JButton personalID_Button = new JButton("Personnummer");
        personalID_Button.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            bestGymEver.getCustomerList().sort(Comparator.comparing(Customer::getPersonalID));//sort by personal ID
            if (reverseListButton.getText().equals(arrowUp))
                Collections.reverse(bestGymEver.getCustomerList());
            makeCustomerListInScrollableWindow(bestGymEver.getCustomerList());
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(personalID_Button);
        gridPanel.add(emptyLabel);

        //Button 4
        JButton membershipButton = new JButton("Registreringsdatum");
        membershipButton.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            bestGymEver.getCustomerList().sort(Comparator.comparing(Customer::getMembershipDate)); //sort by membership date
            if (reverseListButton.getText().equals(arrowUp))
                Collections.reverse(bestGymEver.getCustomerList());
            makeCustomerListInScrollableWindow(bestGymEver.getCustomerList());
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(membershipButton);
        gridPanel.add(emptyLabel);

        //Button 5
        JButton activeButton = new JButton("Visa Aktiva Medlemmar");
        activeButton.addActionListener(e -> {
            List<Customer> activeMembers = new ArrayList<>();
            for (Customer customer: bestGymEver.getCustomerList()) {
                if (customer.isActiveMember()){
                    activeMembers.add(customer);
                }
            }
            activeMembers.sort(Comparator.comparing(Customer::getName));
            jLayeredPane.remove(scrollableTextWindow);
            makeCustomerListInScrollableWindow(activeMembers);
            this.jLayeredPane.add(scrollableTextWindow, 2);
        });
        gridPanel.add(activeButton);
        gridPanel.add(emptyLabel);

        //Button 6
        JButton notActiveButton = new JButton("Visa Inaktiva Medlemmar");
        notActiveButton.addActionListener(e -> {
            jLayeredPane.remove(scrollableTextWindow);
            List<Customer> activeMembers = new ArrayList<>();
            for (Customer customer: bestGymEver.getCustomerList()) {
                if (!customer.isActiveMember()){
                    activeMembers.add(customer);
                }
            }
            activeMembers.sort(Comparator.comparing(Customer::getName));
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

    private void removeLayeredComponents(){
        this.jLayeredPane.removeAll();
        this.jLayeredPane.revalidate();
        this.jLayeredPane.repaint();
        ImagePanel imagePanel = new ImagePanel(new ImageIcon(String.valueOf(bestGymEver.getPathBackgroundImage())).getImage());
        this.jLayeredPane.add(imagePanel, 0);
    }
}
