package GymAndMenuPackage;

import CustomerPackage.Customer;
import UtilityPackage.Utility;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import static GymAndMenuPackage.MainMenu.dialogTitle;
import static GymAndMenuPackage.MainMenu.mainMenu;
import static javax.swing.JOptionPane.*;

public class CustomerMembershipRegistration {

    private static final String registrationTitle = "Best Gym Ever - Registrera Ny Medlem";

    //Ask for customer information, verify and register new customer if correct
    public static void registerNewCustomerViaUserInput(BestGymEver bestGymEver) {
        Customer customer = new Customer();

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


        int choice = showConfirmDialog(bestGymEver.windowMain, signUpPanel, registrationTitle, OK_CANCEL_OPTION);

        String inputName = textFieldName.getText();
        String inputPersonalID = textFieldPersonalID.getText();

        if (choice == OK_OPTION) {
            //Validate format of name and personalID
            if (Utility.validatedSignUpInput(inputName, inputPersonalID)) {
                //Register customer
                customer.setName(inputName);
                customer.setPersonalID(inputPersonalID);
                customer.setMembershipDate(LocalDate.now());
                customer.setActiveMember(true);
                bestGymEver.getCustomerList().add(customer);
                Utility.addCustomerToFile(customer, bestGymEver.getFilePathUpdatedCustomers());
                showMessageDialog(bestGymEver.windowMain, customer.getName() + " är nu registrerad som medlem", registrationTitle, INFORMATION_MESSAGE);
                mainMenu(bestGymEver);
            } else {
                //If format is invalid
                showMessageDialog(bestGymEver.windowMain, "Felaktigt Format!", registrationTitle, WARNING_MESSAGE);
                registerNewCustomerViaUserInput(bestGymEver);
            }
        } else
            mainMenu(bestGymEver);
    }
}
