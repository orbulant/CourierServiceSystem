package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.Feedback;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FeedbackItemHolderController implements Initializable {
    @FXML
    Label txt_label_one;
    @FXML
    Label txt_label_two;
    @FXML
    Label txt_display_one;
    @FXML
    Label txt_display_two;
    @FXML
    Button btn_view_edit;

    private Feedback feedback;
    private FeedbackFormController feedbackFormController;

    public FeedbackItemHolderController(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_label_one.setText("OrderID/Name:");
        txt_label_two.setText("Feedback:");

        txt_display_one.setText(String.join("/", feedback.getOrder().getOrderID(), feedback.getOrder().getOrderName()));
        txt_display_two.setText(feedback.getFeedback());

        btn_view_edit.setOnMouseClicked(e -> {
            try{
                // pass info to delivery form
                feedbackFormController.populateFields(this.feedback);
            } catch (Exception err) {
                err.printStackTrace();
            }
        });
    }

    public FeedbackFormController getFeedbackFormController() {
        return feedbackFormController;
    }

    public void setFeedbackFormController(FeedbackFormController feedbackFormController) {
        this.feedbackFormController = feedbackFormController;
    }
}
