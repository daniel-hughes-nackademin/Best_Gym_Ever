package UtilityPackage;

import CustomerPackage.Customer;
import GymAndMenuPackage.BestGymEver;
import GymAndMenuPackage.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActionListenerFrame extends JFrame implements ActionListener {

    protected JLayeredPane jLayeredPane;
    BestGymEver bestGymEver;
    List<JButton> jButtonList;

    public ActionListenerFrame (BestGymEver bestGymEver){
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

        jButtonList = new ArrayList<>();

    }

    public void displayRegistryMenu(){

        JPanel flowPanelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel gridPanel = new JPanel(new GridLayout(5,1));
        gridPanel.setBackground(Color.BLACK);

        JLabel buttonHeader = new JLabel("Sortera Efter:", JLabel.CENTER);
        buttonHeader.setFont(new Font("Courier New", Font.BOLD, 14));
        buttonHeader.setForeground(Color.WHITE);
        gridPanel.add(buttonHeader);

        jButtonList.add(new JButton("Alfabetisk Ordning"));
        jButtonList.get(0).addActionListener(this);
        gridPanel.add(jButtonList.get(0));

        jButtonList.add(new JButton("Registreringsdatum"));
        jButtonList.get(1).addActionListener(this);
        gridPanel.add(jButtonList.get(1));

        jButtonList.add(new JButton("Visa Aktiva Medlemmar"));
        jButtonList.get(2).addActionListener(this);
        gridPanel.add(jButtonList.get(2));

        jButtonList.add(new JButton("Visa Inaktiva Medlemmar"));
        jButtonList.get(3).addActionListener(this);
        gridPanel.add(jButtonList.get(3));

        jButtonList.add(new JButton("Huvudmeny"));
        jButtonList.get(4).addActionListener(this);
        gridPanel.add(jButtonList.get(4));


        flowPanelButtons.setBounds(230,320, 190, 140);
        flowPanelButtons.setBackground(Color.BLACK);
        flowPanelButtons.add(gridPanel);
        flowPanelButtons.setVisible(true);

        //Adding side buttons to the layeredPane
        this.getLayeredPane().add(flowPanelButtons, 1);

        //Adding scrollable window with customer list to layeredPane
        displayCustomerListInScrollableWindow(bestGymEver);

        //Adding menu button
        this.getLayeredPane().add(jButtonList.get(4),3);
        jButtonList.get(4).setBounds(600, 650, 100, 100);
    }

    private void displayCustomerListInScrollableWindow(BestGymEver bestGymEver){
        //Showing the customer list as a scrollable window
        JPanel scrollableTextWindow = new JPanel();
        Collections.sort(bestGymEver.getCustomerList(), Comparator.comparing(Customer::getName));
        JScrollPane jScrollPane = Utility.getCustomerListInJScrollPane(bestGymEver, bestGymEver.getCustomerList());
        scrollableTextWindow.add(jScrollPane);
        scrollableTextWindow.setBounds(420, 190, 360, 412);
        scrollableTextWindow.setBackground(Color.BLACK);
        this.getLayeredPane().add(scrollableTextWindow, 2);
    }

    public void refreshFrame(){
        this.jLayeredPane.removeAll();
        this.jLayeredPane.revalidate();
        this.jLayeredPane.repaint();
        ImagePanel imagePanel = new ImagePanel(new ImageIcon(bestGymEver.getFilePathBackgroundImage()).getImage());
        this.jLayeredPane.add(imagePanel, 0);
    }

    public JLayeredPane getjLayeredPane() {
        return jLayeredPane;
    }

    public void setjLayeredPane(JLayeredPane jLayeredPane) {
        this.jLayeredPane = jLayeredPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Sorting the customer list alphabetically
        //Collections.sort(bestGymEver.getCustomerList(), Comparator.comparing(Customer::getName));


        if (e.getSource() == jButtonList.get(4)){
            //jButtonList.get(4).setVisible(false);

            this.refreshFrame();
            /*this.jLayeredPane.removeAll();
            this.jLayeredPane.revalidate();
            ImagePanel imagePanel = new ImagePanel(new ImageIcon(bestGymEver.getFilePathBackgroundImage()).getImage());
            jLayeredPane.add(imagePanel, 0);*/
            MainMenu.mainMenu(bestGymEver);
        }
    }
}
