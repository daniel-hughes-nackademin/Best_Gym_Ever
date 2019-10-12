package GymAndMenuPackage;

import UtilityPackage.Utility;
import CustomerPackage.Customer;

import java.time.LocalDate;

import static GymAndMenuPackage.MainMenu.dialogTitle;
import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

//This class has methods that allows to search for a customer and then make changes from there
public class CustomerSearchMenu {

    public static void searchRegistryViaUserInput(BestGymEver bestGymEver) {
        //Update the list, create list if empty
        bestGymEver.setCustomerList(Utility.updateCustomerListFromFile(bestGymEver));

        while (true) {
            String searchInput = showInputDialog(bestGymEver.windowMain, "Skriv in namn (Förnamn Efternamn)\n" +
                                    "eller personnummer (ååmmddnnnn):", dialogTitle, PLAIN_MESSAGE);
            if (searchInput == null) {
                MainMenu.mainMenu(bestGymEver);
            }
            searchInput = searchInput.trim();

            //Verifying searchInput to correct format
            if (!Utility.validatedSearchInput(searchInput)){
                showMessageDialog(bestGymEver.windowMain, "Felaktigt Format!", dialogTitle, WARNING_MESSAGE);
                continue;
            }

            Customer customer = Utility.getCustomerFromList(bestGymEver.getCustomerList(), searchInput);
            if (customer == null) {
                showMessageDialog(bestGymEver.windowMain, "\"" + searchInput + "\" finns inte med i registret!", dialogTitle, WARNING_MESSAGE);
                //ASK IF WE SHOULD ADD CUSTOMER TO REGISTRY AS NEW ACTIVE MEMBER, SUGGEST searchInput as name

            } else {
                showMessageDialog(bestGymEver.windowMain, "Personen finns i registret:\n" + customer, dialogTitle, INFORMATION_MESSAGE);
                runCustomerMenu(bestGymEver, customer);
            }
        }
    }

    public static void runCustomerMenu(BestGymEver bestGymEver, Customer customer) {
        if (customer.isActiveMember()) {
            Object[] options = {"Träna på gymmet och logga i tränarens lista", "Se lista över kundens gympass"};
            Object choice = showInputDialog(bestGymEver.windowMain, "Vad vill " + customer.getName() + " göra?", dialogTitle, PLAIN_MESSAGE, null, options, options[0]);

            if (choice == options[0]) {
                Utility.addGymVisitToFile(customer, bestGymEver.getFilePathGymVisits());
                showMessageDialog(bestGymEver.windowMain, "Träningspass för " + customer.getName() + " är loggat i listan.", dialogTitle, INFORMATION_MESSAGE);
            } else if (choice == options[1]) {
                int nrOfGymVisits = Utility.updateCustomerGymVisitsFromFile(customer, bestGymEver.getFilePathGymVisits());
                showMessageDialog(bestGymEver.windowMain, customer.getName() + " har haft " + nrOfGymVisits + " gymbesök:\n" +
                        customer.showAllVisits(), dialogTitle, INFORMATION_MESSAGE);
            }
        } else {
            activateCustomerMembership(bestGymEver, customer);
        }
    }

    public static void activateCustomerMembership(BestGymEver bestGymEver, Customer customer) {
        int choice = showConfirmDialog(bestGymEver.windowMain, "Vill " + customer.getName() + " betala medlemsavgift och bli medlem?", dialogTitle, YES_NO_OPTION);
        if (choice == YES_OPTION) {
            customer.setMembershipDate(LocalDate.now());
            customer.setActiveMember(true);
            showMessageDialog(bestGymEver.windowMain, customer.getName() + " är nu medlem hos Best Gym Ever! Välkommen!", dialogTitle, INFORMATION_MESSAGE);

            //Here we add the customer to our file "Customer List.txt" and update the actual list
            Utility.addCustomerToFile(customer, bestGymEver.getFilePathUpdatedCustomers());
            bestGymEver.setCustomerList(Utility.updateCustomerListFromFile(bestGymEver));

            runCustomerMenu(bestGymEver, customer);
        } else {
            searchRegistryViaUserInput(bestGymEver);
        }
    }
}
