package GymAndMenuPackage;

import UtilityPackage.Window;
import UtilityPackage.Utility;

import javax.swing.*;

import java.awt.*;

import static javax.swing.JOptionPane.*;

public class MainMenu {

    static Window windowMain;

    static final String dialogTitle = "Best Gym Ever - Kundregister";

    public static void main(String[] args) {
        BestGymEver bestGymEver = new BestGymEver();

        //Creating the frame for the program
        windowMain = new Window(bestGymEver);
        UIManager.put("OptionPane.background", Color.darkGray);
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("Panel.background", Color.darkGray);

        mainMenu(bestGymEver);

    }

    //The main menu for the Best Gym Ever Application
    public static void mainMenu(BestGymEver bestGymEver) {

        //Update the list, create list if empty
        bestGymEver.setCustomerList(Utility.updateCustomerListFromFile(bestGymEver));

        UIManager.put("OptionPane.cancelButtonText", "Avsluta");
        UIManager.put("Button.background", new Color(125, 46, 154));
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("Label.foreground", Color.white);

        Object[] options = {"Sök efter kund i registret", "Visa kundregister", "Registrera ny kund", "Ta bort kund från registret"};
        Object choice = showInputDialog(windowMain, "Vad vill du göra?", "Best Gym Ever - Customer Management Software",
                QUESTION_MESSAGE, null, options, options[0]);
        UIManager.put("OptionPane.cancelButtonText", "Tillbaka");
        if (choice == null) {
            int answer = showConfirmDialog(windowMain, "Är du säker på att du vill avsluta?", dialogTitle, OK_CANCEL_OPTION, WARNING_MESSAGE);
            if (answer == YES_OPTION) {
                System.exit(0);
            } else mainMenu(bestGymEver); //restart mainMenu()
        }


        if (choice == options[0])
            CustomerSearchMenu.searchRegistryViaUserInput(bestGymEver);
        else if (choice == options[1]){
            windowMain.initializeRegistryMenu();
        }
        else if (choice == options[2]){
            CustomerMembershipRegistration.registerNewCustomerViaUserInput(bestGymEver);
        }
        else {
            RemoveCustomerMenu.removeCustomerViaUserInput(bestGymEver);
        }

    }
}
