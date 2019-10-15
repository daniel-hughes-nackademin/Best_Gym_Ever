package GymAndMenuPackage;

import CustomerPackage.Customer;
import UtilityPackage.Utility;

import static GymAndMenuPackage.MainMenu.dialogTitle;
import static GymAndMenuPackage.MainMenu.windowMain;
import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class RemoveCustomerMenu {

    public static void removeCustomerViaUserInput(BestGymEver bestGymEver) {
        //Update the list, create list if empty
        bestGymEver.setCustomerList(Utility.updateCustomerListFromFile(bestGymEver));

        while (true) {
            String searchInput = showInputDialog(windowMain, "Skriv in namn (Förnamn Efternamn)\n" +
                    "eller personnummer (ååmmddnnnn):", "Best Gym Ever - Ta Bort Kund", PLAIN_MESSAGE);
            if (searchInput == null) {
                MainMenu.mainMenu(bestGymEver);
                break;
            }
            searchInput = searchInput.trim();

            //Verifying searchInput to correct format
            if (!Utility.validatedSearchInput(searchInput)){
                showMessageDialog(windowMain, "Felaktigt Format!", dialogTitle, WARNING_MESSAGE);
                continue;
            }

            Customer customer = Utility.getCustomerFromList(bestGymEver.getCustomerList(), searchInput);
            if (customer == null) {
                showMessageDialog(windowMain, "\"" + searchInput + "\" finns inte med i registret!", dialogTitle, WARNING_MESSAGE);
            } else {
                showMessageDialog(windowMain, "Personen finns i registret:\n" + customer, dialogTitle, INFORMATION_MESSAGE);
                int choice = showConfirmDialog(windowMain, "Är du säker på att du vill ta bort " + customer.getName() + "?", dialogTitle, OK_CANCEL_OPTION, WARNING_MESSAGE);
                if (choice == OK_OPTION){
                    if (bestGymEver.getCustomerList().isEmpty())
                        showMessageDialog(windowMain, "Det finns inga kunder i registret!", dialogTitle, WARNING_MESSAGE);
                    else {
                        Utility.removeCustomerFromFile(bestGymEver, customer);
                        showMessageDialog(windowMain, customer.getName() + " är nu borttagen från registret.", dialogTitle, INFORMATION_MESSAGE);
                    }
                }
                MainMenu.mainMenu(bestGymEver);
                break;
            }
        }
    }
}
