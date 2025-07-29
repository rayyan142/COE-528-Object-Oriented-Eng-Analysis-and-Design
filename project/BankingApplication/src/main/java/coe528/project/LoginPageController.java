package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogIn;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        String user = txtUsername.getText();
        String pass = txtPassword.getText();

        if ("admin".equals(user) && "admin".equals(pass)) {
            App.setRoot("managerPage");
        } else {
            Customer customer = App.getCustomer(user);
            if (customer != null && customer.getPassword().equals(pass)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerPage.fxml"));
                Parent root = loader.load();
                CustomerPageController customerController = loader.getController();
                customerController.setCurrentCustomer(customer);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.out.println("Invalid credentials!");
            }
        }
    }

    @FXML
    void initialize() {
        assert btnLogIn != null : "fx:id=\"btnLogIn\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert lblPassword != null : "fx:id=\"lblPassword\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert lblUsername != null : "fx:id=\"lblUsername\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'loginPage.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'loginPage.fxml'.";

    }

}

