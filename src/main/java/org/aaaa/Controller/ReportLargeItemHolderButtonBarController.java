package org.aaaa.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandlerReport;


import java.net.URL;

import java.util.ResourceBundle;

public class ReportLargeItemHolderButtonBarController implements Initializable {
    @FXML
    Button btn_Accounts;
    @FXML
    Button btn_Users;
    @FXML
    Button btn_Orders;
    @FXML
    Button btn_Feedbacks;
    @FXML
    Label lbl_Title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_Title.setText("Generate report");
    }

    @FXML
    public void handleAccounts(){
        FileHandlerReport fhReport = new FileHandlerReport(DatabasePath.Account.getName());
        try {
            fhReport.createPdf(System.getProperty("user.home") + "/Desktop/accounts.pdf", "Accounts", "accounts");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Accounts reports successfully generated!");
            alert.setContentText("Please check your desktop.");

            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error Generating Report");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
            e.printStackTrace();
        }
    }
    @FXML
    public void handleUsers(){
        FileHandlerReport fhReport = new FileHandlerReport(DatabasePath.Account.getName());
        try {
            fhReport.createPdf(System.getProperty("user.home") + "/Desktop/users.pdf", "Users", "users");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Accounts reports successfully generated!");
            alert.setContentText("Please check your desktop.");

            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error Generating Report");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
            e.printStackTrace();
        }
    }
    @FXML
    public void handleOrders(){
        FileHandlerReport fhReport = new FileHandlerReport(DatabasePath.Account.getName());
        try {
            fhReport.createPdf(System.getProperty("user.home") + "/Desktop/orders.pdf", "Orders", "orders");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Accounts reports successfully generated!");
            alert.setContentText("Please check your desktop.");

            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error Generating Report");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
            e.printStackTrace();
        }
    }
    @FXML
    public void handleFeedbacks(){
        FileHandlerReport fhReport = new FileHandlerReport(DatabasePath.Account.getName());
        try {
            fhReport.createPdf(System.getProperty("user.home") + "/Desktop/feedbacks.pdf", "Feedbacks", "feedbacks");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Accounts reports successfully generated!");
            alert.setContentText("Please check your desktop.");

            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error Generating Report");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
