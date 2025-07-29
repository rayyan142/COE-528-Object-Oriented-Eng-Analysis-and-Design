package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CustomerPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDeposit;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnOnlinePurchase;

    @FXML
    private Button btnWithdraw;

    @FXML
    private Label lblAmount;

    @FXML
    private TextField txtAmount;
    
    @FXML
    private Label lblBalanceAmount, lblAction;
    
    private Customer currentCustomer;

    @FXML
    private void handleDeposit(ActionEvent event) {
        try {
            int amount = Integer.parseInt(txtAmount.getText());
            currentCustomer.deposit(amount);
            lblAction.setText("Deposit Successful.");
            updateCustomerDisplay();
        } catch (NumberFormatException e) {
            lblAction.setText("Invalid amount.");
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        currentCustomer = null;
        App.setRoot("LoginPage");
    }

    @FXML
    private void handleOnlinePurchase(ActionEvent event) {
        try {
            int amount = Integer.parseInt(txtAmount.getText());
            if (amount < 50) {
                lblAction.setText("Minimum amount for online purchase is $50.");
                return;
            }
            if (currentCustomer.onlinePurchase(amount)) {
                lblAction.setText("Purchase Successful.");
                updateCustomerDisplay();
            } else {
                lblAction.setText("Insufficient funds or error.");
            }
        } catch (NumberFormatException e) {
            lblAction.setText("Invalid amount.");
        }
    }

    @FXML
    private void handleWithdraw(ActionEvent event) {
        try {
            int amount = Integer.parseInt(txtAmount.getText());
            if (currentCustomer.withdraw(amount)) {
                lblAction.setText("Withdrawal Successful.");
                updateCustomerDisplay();
            } else {
                lblAction.setText("Insufficient funds.");
            }
        } catch (NumberFormatException e) {
            lblAction.setText("Invalid amount.");
        }
    }
    
    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
        updateCustomerDisplay();
    }

    private void updateCustomerDisplay() {
        if (currentCustomer != null) {
            lblBalanceAmount.setText("Balance: $" + currentCustomer.getBalance());
        }
    }

    @FXML
    void initialize() {
        assert btnDeposit != null : "fx:id=\"btnDeposit\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert btnOnlinePurchase != null : "fx:id=\"btnOnlinePurchase\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert btnWithdraw != null : "fx:id=\"btnWithdraw\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert lblAmount != null : "fx:id=\"lblAmount\" was not injected: check your FXML file 'customerPage.fxml'.";
        assert txtAmount != null : "fx:id=\"txtAmount\" was not injected: check your FXML file 'customerPage.fxml'.";
        if (currentCustomer != null) {
            updateCustomerDisplay();
        }
    }

}
