package UtilityPackage;

import GymAndMenuPackage.BestGymEver;
import CustomerPackage.GymVisit;
import CustomerPackage.Customer;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {

    //CUSTOMER LIST METHODS
    //Updates the customer list of input BestGymEver. If the updated file is empty, a list is generated from the initial customers file
    public static List<Customer> updateCustomerListFromFile(BestGymEver bestGymEver) {
        List<Customer> customerList;

        File updatedCustomerFile = new File(bestGymEver.getFilePathUpdatedCustomers());

        //If Customer List file does not exist, create it from original customers.txt file
        if (!updatedCustomerFile.exists()) {
            customerList = getCustomerListFromFile(bestGymEver.getFilePathOriginalCustomers());
            writeCustomerListToFile(customerList, bestGymEver.getFilePathUpdatedCustomers());
        } else { //If we have an updated customer list, get that one
            customerList = getCustomerListFromFile(bestGymEver.getFilePathUpdatedCustomers());
        }
        return customerList;
    }

    //Returns a list extracted from the input file path
    public static List<Customer> getCustomerListFromFile(String filePath) {
        List<Customer> customerList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                Customer customer = new Customer();
                String lineFromFile = scanner.nextLine().trim();
                customer.setPersonalID(lineFromFile.substring(0, lineFromFile.indexOf(',')));
                customer.setName(lineFromFile.substring(lineFromFile.indexOf(',') + 2));

                lineFromFile = scanner.nextLine().trim();
                customer.setMembershipDate(LocalDate.parse(lineFromFile, DateTimeFormatter.ISO_LOCAL_DATE));
                customer.setActiveMember(customer.verifyIfActiveMember());

                customerList.add(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    //Customer list is written to file and overwrites if the file exists
    public static void writeCustomerListToFile(List<Customer> customerList, String filePath) {

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Customer customer : customerList) {
                writer.println(customer.getPersonalID() + ", " + customer.getName());
                writer.println(customer.getMembershipDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //CUSTOMER METHODS
    //Returns Customer from input customer list if searchInput equals name or personalID of a customer in the list
    public static Customer getCustomerFromList(List<Customer> customerList, String searchInput) {
        Customer customer = null;

        for (Customer person : customerList) {
            if (searchInput.equalsIgnoreCase(person.getName()) || searchInput.equals(person.getPersonalID())) {
                customer = person;
            }
        }
        return customer;
    }

    //Customer is added at the end of the file
    public static void addCustomerToFile(Customer customer, String filePath) {

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            writer.println(customer.getPersonalID() + ", " + customer.getName());
            writer.println(customer.getMembershipDate());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void removeCustomerFromFile(BestGymEver bestGymEver, Customer customer){
        bestGymEver.getCustomerList().remove(customer);
        Utility.writeCustomerListToFile(bestGymEver.getCustomerList(), bestGymEver.getFilePathUpdatedCustomers());
    }


    //GYM VISIT METHODS
    //Gym Visit is added at the end of the file, or written into a new file if input file path doesn't exist
    public static void addGymVisitToFile(Customer customer, String filePath) {
        LocalDateTime checkInTime = LocalDateTime.now().withNano(0).withSecond(0);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            writer.println(customer.getPersonalID() + ", " + customer.getName());
            writer.println(checkInTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Updates the GymVisits list for input customer and returns number of GymVisits
    public static int updateCustomerGymVisitsFromFile(Customer customer, String filePath) {
        customer.getGymVisits().clear();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                boolean correctCustomer = false;
                String lineFromFile = scanner.nextLine().trim();
                if (customer.getPersonalID().equals(lineFromFile.substring(0, lineFromFile.indexOf(',')))) {
                    correctCustomer = true;
                }
                lineFromFile = scanner.nextLine().trim();
                if (correctCustomer) {
                    GymVisit visit = new GymVisit(LocalDateTime.parse(lineFromFile));
                    customer.getGymVisits().add(visit);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return customer.getGymVisits().size();
    }


    //PRESENTATION METHODS
    public static String formatStringToFirstLastName(String fullName){
        String first = fullName.substring(0,1).toUpperCase() + fullName.substring(1, fullName.indexOf(' ')).toLowerCase();
        String last = fullName.substring(fullName.indexOf(' ')+1, fullName.indexOf(' ')+2).toUpperCase() +
                fullName.substring(fullName.indexOf(' ')+2).toLowerCase();
        return first + ' ' + last;
    }


    //Returns String presentation of the input customer list
    public static String getCustomerListAsString(List<Customer> customerList) {
        StringBuilder list = new StringBuilder();
        for (Customer customer : customerList) {
            list.append(customer).append("\n");
        }
        return list.toString();
    }

    //Returns a JScrollPane with the input customer list
    public static JScrollPane getCustomerListInJScrollPane(List<Customer> customerList) {
        JTextArea textArea = new JTextArea(25, 0);
        textArea.setText(getCustomerListAsString(customerList));
        textArea.setEditable(false);
        return new JScrollPane(textArea);
    }

    //Prints the input customer list to the console
    public static void printCustomerList(List<Customer> customerList) {
        for (Customer p : customerList) {
            System.out.println(p);
        }
    }


    //VALIDATION METHODS
    //Validates input for the format (firstName lastName) or (yyyymmddnnnn)
    public static boolean validatedSearchInput(String searchInput) {
        boolean isCorrectFormat = false;
        boolean isCorrectNameFormat = false;
        boolean isCorrectPersonalIDFormat = false;

        if (isLettersAndSpacesOnly(searchInput) && numberOfSpaces(searchInput) == 1)
            isCorrectNameFormat = true;

        if (isDigitsOnly(searchInput) && searchInput.length() == 10)
            isCorrectPersonalIDFormat = true;


        if (isCorrectNameFormat || isCorrectPersonalIDFormat)
            isCorrectFormat = true;

        return isCorrectFormat;
    }

    //Validates inputName == correct name and inputPersonalID is correct PersonalID
    public static boolean validatedSignUpInput(String name, String personalID) {
        boolean isCorrectFormat = false;
        boolean isCorrectNameFormat = false;
        boolean isCorrectPersonalIDFormat = false;

        if (isLettersAndSpacesOnly(name) && numberOfSpaces(name) == 1)
            isCorrectNameFormat = true;

        if (isDigitsOnly(personalID) && personalID.length() == 10){
            String date = "20" + personalID.substring(0,2) + '-' + personalID.substring(2,4) + '-' + personalID.substring(4,6);
            try {
                LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                isCorrectPersonalIDFormat = true;
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }

        }

        if (isCorrectNameFormat && isCorrectPersonalIDFormat)
            isCorrectFormat = true;

        return isCorrectFormat;
    }

    //Returns number of spaces in a text
    public static int numberOfSpaces(String inputText) {
        int spaces = 0;

        if (inputText != null) {
            for (int i = 0; i < inputText.length(); i++) {
                char c = inputText.charAt(i);
                if (c == ' ')
                    spaces++;
            }
        }
        return spaces;
    }

    //Returns true if a text only contains letters and spaces
    public static boolean isLettersAndSpacesOnly(String inputText) {
        boolean isCorrect = true;

        if (inputText == null)
            isCorrect = false;
        else {
            for (int i = 0; i < inputText.length(); i++) {
                char c = inputText.charAt(i);
                if (!Character.isLetter(c) && c != ' ')
                    isCorrect = false;
            }
        }

        return isCorrect;
    }

    //Returns true if a text consists of only digits
    public static boolean isDigitsOnly(String inputText) {
        boolean isCorrect = true;

        if (inputText == null)
            isCorrect = false;
        else {
            for (int i = 0; i < inputText.length(); i++) {
                char c = inputText.charAt(i);
                if (!Character.isDigit(c))
                    isCorrect = false;
            }
        }

        return isCorrect;
    }


}
