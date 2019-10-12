package UtilityPackage;

import GymAndMenuPackage.BestGymEver;
import GymAndMenuPackage.CustomerRegistryMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerFrame extends JFrame implements ActionListener {

    public ActionListenerFrame (String title){
        super(title);
    }







    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == CustomerRegistryMenu.jButtonList.get(0)){

        }
    }
}
