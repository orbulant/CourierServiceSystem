package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class FeedbackSearchbarController extends SearchbarController implements Initializable {
    private FeedbackListViewerController feedbackListViewerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search_text_field.setOnAction(e -> {
            search_text_field.setPromptText("");
        });

        search_text_field.setOnKeyPressed(e -> {
            feedbackListViewerController.populateFeedbacks(this.search_text_field.getText());
        });
    }

    public FeedbackListViewerController getFeedbackListViewerController() {
        return feedbackListViewerController;
    }

    public void setFeedbackListViewerController(FeedbackListViewerController feedbackListViewerController) {
        this.feedbackListViewerController = feedbackListViewerController;
    }

}
