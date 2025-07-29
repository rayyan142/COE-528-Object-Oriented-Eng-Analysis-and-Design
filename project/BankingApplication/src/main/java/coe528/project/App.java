package coe528.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Stage primaryStage;
    private static final Manager managerInstance = new Manager("admin", "admin");

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("Bank Application");
        setRoot("loginPage");
        primaryStage.show();
        App.loadCustomers();
    }

    public static void setRoot(String fxml) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource(fxml + ".fxml"));
        primaryStage.setScene(new Scene(root));
    }
    
    public static void loadCustomers() throws IOException {
        customers.clear();

        File dir = new File("customers");
        File[] fileList = dir.listFiles();

        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    List<String> lines = Files.readAllLines(file.toPath());
                    if (lines.size() >= 3) {
                        String username = lines.get(0);
                        String password = lines.get(1);
                        double balance = Double.parseDouble(lines.get(2));

                        Customer customer = new Customer(username, password, balance);
                        customers.add(customer);
                    }
                }
            }
        }
    }

    public static void addCustomerToList(Customer customer) {
        customers.add(customer);
    }

    public static void removeCustomer(String username) {
        customers.removeIf(c -> c.getUsername().equals(username));
    }
    
    public static Customer getCustomer(String username) {
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }
    
    public static Manager getManager() {
        return managerInstance;
    }

    public static boolean checkCustomerCredentials(String username, String password) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
