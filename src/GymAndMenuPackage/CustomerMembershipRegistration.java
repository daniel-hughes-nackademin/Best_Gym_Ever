package GymAndMenuPackage;

import CustomerPackage.Customer;
import UtilityPackage.Utility;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import static GymAndMenuPackage.MainMenu.*;
import static javax.swing.JOptionPane.*;

class CustomerMembershipRegistration {

    private static final String registrationTitle = "Best Gym Ever - Registrera Ny Medlem";

    //Ask for customer information, verify and register new customer if correct
    static void registerNewCustomerViaUserInput(BestGymEver bestGymEver) {
        JPanel signUpPanel = new JPanel(new GridLayout(0, 2));

        JLabel mustPayMessage = new JLabel("OBS! Kunden måste betala innan registrering!");
        mustPayMessage.setFont(new Font("Courier New", Font.BOLD, 14));
        JLabel emptyLabel = new JLabel("");
        JLabel askName = new JLabel("Fyll i namn (förnamn efternamn):");
        JLabel askPersonalID = new JLabel("Fyll i personnummer (ååmmddnnnn):");
        JTextField textFieldName = new JTextField();
        JTextField textFieldPersonalID = new JTextField();


        signUpPanel.add(mustPayMessage);
        signUpPanel.add(emptyLabel);
        signUpPanel.add(askName);
        signUpPanel.add(textFieldName);
        signUpPanel.add(askPersonalID);
        signUpPanel.add(textFieldPersonalID);


        int choice = showConfirmDialog(windowMain, signUpPanel, registrationTitle, OK_CANCEL_OPTION);

        String inputName = textFieldName.getText().trim();
        String inputPersonalID = textFieldPersonalID.getText().trim();

        if (choice == OK_OPTION) {

            if (Utility.getCustomerFromList(bestGymEver.getCustomerList(), inputName) != null){
                showMessageDialog(windowMain, Utility.formatStringToFirstLastName(inputName) + " är redan registrerad!", registrationTitle, WARNING_MESSAGE);
                registerNewCustomerViaUserInput(bestGymEver);
            }
            else if (Utility.getCustomerFromList(bestGymEver.getCustomerList(), inputPersonalID) != null){
                showMessageDialog(windowMain, "Personnumret " + inputPersonalID + " är redan registrerat!", registrationTitle, WARNING_MESSAGE);
                registerNewCustomerViaUserInput(bestGymEver);
            }
            //Validate format of name and personalID
            else if (Utility.validatedSignUpInput(inputName, inputPersonalID)) {
                Customer customer = new Customer();

                //Register customer
                customer.setName(Utility.formatStringToFirstLastName(inputName));
                customer.setPersonalID(inputPersonalID);
                customer.setMembershipDate(LocalDate.now());
                customer.setActiveMember(true);
                bestGymEver.getCustomerList().add(customer);
                Utility.addCustomerToFile(customer, bestGymEver.getPathUpdatedCustomers());
                showMessageDialog(windowMain, customer.getName() + " är nu registrerad som medlem", registrationTitle, INFORMATION_MESSAGE);
                mainMenu(bestGymEver);

            } else {
                //If format is invalid
                showMessageDialog(windowMain, "Felaktigt Format!", registrationTitle, WARNING_MESSAGE);
                registerNewCustomerViaUserInput(bestGymEver);
            }
        } else //If cancel or closing window
            mainMenu(bestGymEver);
    }
}
