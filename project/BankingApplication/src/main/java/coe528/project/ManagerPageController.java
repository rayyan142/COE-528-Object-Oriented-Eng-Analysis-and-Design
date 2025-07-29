package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ManagerPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lblDeleteUsername;

    @FXML
    private Label lblNewPassword;

    @FXML
    private Label lblNewUsername;

    @FXML
    private TextField txtDeleteUsername;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtNewUsername;

    @FXML
    private void handleAddCustomer(ActionEvent event) {
        String username = txtNewUsername.getText();
        String password = txtNewPassword.getText();
        boolean addedSuccessfully = App.getManager().addCustomer(username, password, 100.0);

        if (addedSuccessfully) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add customer.");
        }

        txtNewUsername.clear();
        txtNewPassword.clear();
    }
    
    @FXML
    private void handleDeleteCustomer(ActionEvent event) {
        String username = txtDeleteUsername.getText();
        boolean isDeleted = App.getManager().deleteCustomer(username);
        if (isDeleted) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Failed to delete customer.");
        }
        txtDeleteUsername.clear();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        App.setRoot("LoginPage");
    }

    @FXML
    void initialize() {
        assert btnAddUser != null : "fx:id=\"btnAddUser\" was not injected: check your FXML file 'managerPage.fxml'.";
        assert btnDeleteUser != null : "fx:id=\"btnDeleteUser\" was not injected: check your FXML file 'managerPage.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'managerPage.fxml'.";
        assert lblDeleteUsername != null : "fx:id=\"lblDeleteUsername\" was not injected: check your FXML file 'managerPage.fxml'.";
        assert lblNewPassword != null : "fx:id=\"lblNewPassword\" was not injected: check your FXML file 'managerPage.fxml'.";
        assert lblNewUsername != null : "fx:id=\"lblNewUsername\" was not injected: check your FXML file 'managerPage.fxml'.";
        assert txtDeleteUsername != null : "fx:id=\"txtDeleteUsername\" was not injected: check your FXML file 'managerPage.fxml'.";
        assert txtNewPassword != null : "fx:id=\"txtNewPassword\" was not injected: check your FXML file 'managerPage.fxml'.";
        assert txtNewUsername != null : "fx:id=\"txtNewUsername\" was not injected: check your FXML file 'managerPage.fxml'.";

    }

}

