package org.aaaa.Controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.aaaa.Feedback;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FeedbackFormController implements Initializable {
    @FXML
    Label ui_label_one;
    @FXML
    Label ui_label_two;
    @FXML
    Label ui_label_three;
    @FXML
    Label ui_label_four;
    @FXML
    Label ui_label_five;
    @FXML
    Button ui_button_clear;

    private Feedback feedback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void populateFields(Feedback feedback) {
        this.feedback = feedback;

        ui_label_one.setText(this.getFormattedOrderInfo());
        ui_label_two.setText(this.getFormattedRecipientInfo());
        ui_label_three.setText(this.getFormattedDeliveryInfo());
        ui_label_four.setText(this.feedback.getOrder().getStatus());
        ui_label_five.setText(this.feedback.getFeedback());

        ui_button_clear.setOnAction(e -> {
            ui_label_one.setText("-");
            ui_label_two.setText("-");
            ui_label_three.setText("-");
            ui_label_four.setText("-");
            ui_label_five.setText("-");
        });
    }

    private String getFormattedOrderInfo() {
        return String.join("/", this.feedback.getOrder().getOrderID(), this.feedback.getOrder().getOrderName());
    }

    private String getFormattedRecipientInfo() {
        return String.join("@", this.feedback.getOrder().getAccount().getName(), this.feedback.getOrder().getAccount().getContactNum());
    }

    private String getFormattedDeliveryInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.join(" ON ", this.feedback.getOrder().getAssignTo().getName(), this.feedback.getOrder().getCreatedOn().format(formatter));
    }
}
