package GymAndMenuPackage;

import UtilityPackage.Utility;
import CustomerPackage.Customer;

import java.time.LocalDate;

import static GymAndMenuPackage.MainMenu.*;
import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

//This class has methods that allows to search for a customer and then make changes from there
class CustomerSearchMenu {

   static void searchRegistryViaUserInput(BestGymEver bestGymEver) {
        //Update the list, create list if empty
        bestGymEver.setCustomerList(Utility.updateCustomerListFromFile(bestGymEver));

       String searchInput = null;
       boolean isCorrectInput = false;

        while (!isCorrectInput) {
            searchInput = showInputDialog(windowMain, "Skriv in namn (Förnamn Efternamn)\n" +
                                    "eller personnummer (ååmmddnnnn):", dialogTitle, PLAIN_MESSAGE);
            if (searchInput == null) {
                MainMenu.mainMenu(bestGymEver);
                return;
            }
                searchInput = searchInput.trim();

            //Verifying searchInput to correct format
            if (Utility.validatedSearchInput(searchInput))
                isCorrectInput = true;
            else
                showMessageDialog(windowMain, "Felaktigt Format!", dialogTitle, WARNING_MESSAGE);
            }

            Customer customer = Utility.getCustomerFromList(bestGymEver.getCustomerList(), searchInput);
            if (customer == null) {
                showMessageDialog(windowMain, "\"" + searchInput + "\" finns inte med i registret!", dialogTitle, WARNING_MESSAGE);
            } else {
                showMessageDialog(windowMain, "Personen finns i registret:\n" + customer, dialogTitle, INFORMATION_MESSAGE);
                runCustomerMenu(bestGymEver, customer);
            }
        }

    private static void runCustomerMenu(BestGymEver bestGymEver, Customer customer) {
        if (customer.isActiveMember()) {
            Object[] options = {"Träna på gymmet och logga i tränarens lista", "Se lista över kundens gympass"};
            Object choice = showInputDialog(windowMain, "Vad vill " + customer.getName() + " göra?", dialogTitle, PLAIN_MESSAGE, null, options, options[0]);

            if (choice == null)
                MainMenu.mainMenu(bestGymEver);
            else if (choice == options[0]) {
                Utility.addGymVisitToFile(customer, bestGymEver.getPathGymVisits());
                showMessageDialog(windowMain, "Träningspass för " + customer.getName() + " är loggat i listan.", dialogTitle, INFORMATION_MESSAGE);
                mainMenu(bestGymEver);
            } else if (choice == options[1]) {
                int nrOfGymVisits = Utility.updateCustomerGymVisitsFromFile(customer, bestGymEver.getPathGymVisits());
                showMessageDialog(windowMain, customer.getName() + " har haft " + nrOfGymVisits + " gymbesök:\n" +
                        customer.showAllVisits(), dialogTitle, INFORMATION_MESSAGE);
                mainMenu(bestGymEver);
            }
        } else {
            activateCustomerMembership(bestGymEver, customer);
        }
    }

   private static void activateCustomerMembership(BestGymEver bestGymEver, Customer customer) {
        int choice = showConfirmDialog(windowMain, "Vill " + customer.getName() + " betala medlemsavgift och bli medlem?", dialogTitle, YES_NO_OPTION);
        if (choice == YES_OPTION) {
            //We remove the old version with an inactive customer from the list
            Utility.removeCustomerFromFile(bestGymEver, customer);

            //We make the customer active
            customer.setMembershipDate(LocalDate.now());
            customer.setActiveMember(true);
            showMessageDialog(windowMain, customer.getName() + " är nu medlem hos Best Gym Ever! Välkommen!", dialogTitle, INFORMATION_MESSAGE);

            //Here we add the customer to our file "Customer List.txt" and update the actual list
            Utility.addCustomerToFile(customer, bestGymEver.getPathUpdatedCustomers());
            bestGymEver.setCustomerList(Utility.updateCustomerListFromFile(bestGymEver));

            runCustomerMenu(bestGymEver, customer);
        } else {
            searchRegistryViaUserInput(bestGymEver);
        }
    }
}
