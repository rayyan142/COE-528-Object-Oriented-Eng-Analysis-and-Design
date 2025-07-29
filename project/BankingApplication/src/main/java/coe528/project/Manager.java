package coe528.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Manager extends User {

    public Manager(String username, String password) {
        super(username, password);
    }

    public boolean addCustomer(String username, String password, double initialBalance) {
        File customerFile = new File(username + ".txt");
        if (customerFile.exists()) {
            System.out.println("Customer already exists.");
            return false;
        } else {
            try (FileWriter writer = new FileWriter(customerFile)) {
                writer.write(username + System.lineSeparator());
                writer.write(password + System.lineSeparator());
                writer.write("100");
                writer.flush();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the customer file.");
                e.printStackTrace();
                return false;
            }

            Customer newCustomer = new Customer(username, password, 100);
            App.addCustomerToList(newCustomer);
            return true;
        }
    }

    public boolean deleteCustomer(String username) {
        File customerFile = new File(username + ".txt");
        if (customerFile.delete()) {
            App.removeCustomer(username);
            System.out.println("Customer " + username + " deleted successfully.");
            return true;
        } else {
            System.out.println("Failed to delete the customer file for " + username);
            return false;
        }
    }
}
