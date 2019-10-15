package GymAndMenuPackage;

import UtilityPackage.Window;
import UtilityPackage.Utility;

import static javax.swing.JOptionPane.*;

public class MainMenu {

    protected static Window windowMain;

    protected static final String dialogTitle = "Best Gym Ever - Kundregister";

    public static void main(String[] args) {
        BestGymEver bestGymEver = new BestGymEver();

        //Creating the frame for the program
        windowMain = new Window(bestGymEver);
        mainMenu(bestGymEver);




        //CHANGE TO PATH INSTEAD OF STRING

        // ENABLE SORTING BY FIRST NAME, LAST NAME

        //ADD BUTTON TO SORT WITH NEWEST/OLDEST ETC - ARROW DOWN AND UP DEPENDING, BOOLEAN

        //FIX WINDOW MINIMIZATION BUG
    }

    //The main menu for the Best Gym Ever Application
    public static void mainMenu(BestGymEver bestGymEver) {

        //Update the list, create list if empty
        bestGymEver.setCustomerList(Utility.updateCustomerListFromFile(bestGymEver));

        Object[] options = {"Sök efter kund i registret", "Visa kundregister", "Registrera ny kund", "Ta bort kund från registret"};
        Object choice = showInputDialog(windowMain, "Vad vill du göra?", "Best Gym Ever - Customer Management Software",
                QUESTION_MESSAGE, null, options, options[0]);
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
