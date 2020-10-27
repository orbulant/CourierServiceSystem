package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Feedback;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.GUIPath;
import org.aaaa.FileHandlers.FileHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class FeedbackListViewerController extends ListViewerController implements Initializable {
    @FXML
    ScrollPane content_pane;
    @FXML
    VBox content;
    
    private FileHandler fileHandler;
    private FeedbackFormController feedbackFormController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fileHandler = new FileHandler(DatabasePath.Feedback.getName());
    }

    public void populateFeedbacks(String filter) {
        try{
            content.getChildren().clear();
            List<List<String>> temp = fileHandler.getContent(DatabasePath.Feedback.getDataLength());
            FXMLLoader loader = new FXMLLoader();
            for (int i = 0; i < temp.size(); i++) {
                loader = new FXMLLoader(getClass().getResource(GUIPath.FeedbackItemHolder.getName()));

                if(temp.get(i).toString().contains(filter)) {
                    // add item controller here
                    FeedbackItemHolderController feedbackItemHolderController = new FeedbackItemHolderController(new Feedback(temp.get(i)));
                    feedbackItemHolderController.setFeedbackFormController(feedbackFormController);
                    loader.setController(feedbackItemHolderController);
                    content.getChildren().add((Node)loader.load());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FeedbackFormController getFeedbackFormController() {
        return feedbackFormController;
    }

    public void setFeedbackFormController(FeedbackFormController feedbackFormController) {
        this.feedbackFormController = feedbackFormController;
    }
}

