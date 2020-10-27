package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.Enums.GUIPath;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class FeedbackMainController implements Initializable, BaseControllerInterface {
    @FXML
    AnchorPane ui_tall_pane_one;
    @FXML
    AnchorPane ui_tall_pane_two;
    @FXML
    AnchorPane ui_tall_pane_three;
    
    private DashboardController dashboardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            // feedback list
            FXMLLoader feedbackList = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            FeedbackListViewerController feedbackListController = new FeedbackListViewerController();
            feedbackList.setController(feedbackListController);

            // searchbar
            FXMLLoader searchbar = new FXMLLoader(getClass().getResource(GUIPath.Searchbar.getName()));
            // set custom controller to searchbar
            FeedbackSearchbarController feedbackSearchbarController = new FeedbackSearchbarController();
            feedbackSearchbarController.setFeedbackListViewerController(feedbackListController);
            searchbar.setController(feedbackSearchbarController);

            // feedback form
            FXMLLoader feedbackForm = new FXMLLoader(getClass().getResource(GUIPath.FeedbackForm.getName()));
            FeedbackFormController feedbackFormController = new FeedbackFormController();
            feedbackForm.setController(feedbackFormController);

            ui_tall_pane_one.getChildren().add((Node) searchbar.load());
            ui_tall_pane_two.getChildren().add((Node) feedbackList.load());
            ui_tall_pane_three.getChildren().add((Node) feedbackForm.load());

            feedbackListController.setTitle("Feedbacks");
            feedbackListController.setTitleButtonVisibility(false);
            feedbackListController.setFeedbackFormController(feedbackFormController);

            Platform.runLater(() -> {
                feedbackListController.populateFeedbacks("");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public DashboardController getDashboardController() {
        return this.dashboardController;
    }

    @Override
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
