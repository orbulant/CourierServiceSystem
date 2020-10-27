package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandlerOrder;
import org.aaaa.FileHandlers.FileHandlerUser;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ReportItemHolderController implements Initializable {
    @FXML
    Label txt_title;
    @FXML
    Label txt_total;

    private String type;

    public ReportItemHolderController(String type) {
        this.type = type;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.txt_title.setText(getFormattedTitle(type));
        FileHandlerUser fileHandlerUser = new FileHandlerUser(DatabasePath.Staff.getName());
        FileHandlerOrder fileHandlerOrder = new FileHandlerOrder(DatabasePath.Order.getName());
        // FileHandlerFeedback fileHandlerFeedback = new FileHandlerFeedback(DatabasePath.Feedback.getName());

        switch(type) {
            case "user":
                this.txt_total.setText(Integer.toString(fileHandlerUser.getTotalUsers()));
                break;
            case "feedback":
                break;
            case "delivered":
                this.txt_total.setText(Integer.toString(fileHandlerOrder.getTotalDelivered()));
                break;
            case "cancelled":
                this.txt_total.setText(Integer.toString(fileHandlerOrder.getTotalCancelled()));
                break;
        }
    }

    private String getFormattedTitle(String title) {
        return String.join(" ", "Total", title);
    }
}
