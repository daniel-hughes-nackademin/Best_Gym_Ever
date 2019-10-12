package GymAndMenuPackage;

import CustomerPackage.Customer;
import UtilityPackage.Utility;

import java.util.List;

import static javax.swing.JOptionPane.*;

public class MainMenu {

    protected static final String dialogTitle = "Best Gym Ever - Kundregister";

    public static void main(String[] args) {
         BestGymEver bestGymEver = new BestGymEver();
         mainMenu(bestGymEver);

        //RUN MINIMUM 3 TESTS IN TEST CLASSES!!!

        //DISPLAY CUSTOMER LIST. SHOW NR OF GYM VISITS & EXPIRATION DATE?
        // ENABLE SORTING BY FIRST NAME, LAST NAME, MEMBERSHIP DATE, SHOW ONLY ACTIVE/NON-ACTIVE MEMBERS?
    }

    //The main menu for the Best Gym Ever Application
    public static void mainMenu(BestGymEver bestGymEver) {
        //Update the list, create list if empty
        bestGymEver.setCustomerList(Utility.updateCustomerListFromFile(bestGymEver));

        Object[] options = {"Sök efter kund i registret", "Visa kundregister", "Registrera ny kund"};
        Object choice = showInputDialog(bestGymEver.windowMain, "Vad vill du göra?", "Best Gym Ever - Customer Management Software",
                QUESTION_MESSAGE, null, options, options[0]);
        if (choice == null) {
            int answer = showConfirmDialog(bestGymEver.windowMain, "Är du säker på att du vill avsluta?", dialogTitle, WARNING_MESSAGE);
            if (answer == YES_OPTION) {
                System.exit(0);
            } else mainMenu(bestGymEver); //restart mainMenu()
        }

        if (choice == options[0])
            CustomerSearchMenu.searchRegistryViaUserInput(bestGymEver);
        else if (choice == options[1]){
            //FINISH METHOD!
            CustomerRegistryMenu.customerRegistryMenu(bestGymEver);
        }
        else {
            CustomerMembershipRegistration.registerNewCustomerViaUserInput(bestGymEver);
        }





    }

    public static String getDialogTitle() {
        return dialogTitle;
    }
}
