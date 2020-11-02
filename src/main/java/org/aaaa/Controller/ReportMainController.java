package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.Enums.GUIPath;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ReportMainController implements Initializable, BaseControllerInterface {
    @FXML
    AnchorPane box_small_one;
    @FXML
    AnchorPane box_small_two;
    @FXML
    AnchorPane box_small_three;
    @FXML
    AnchorPane box_small_four;
    @FXML
    AnchorPane box_large_one;
    @FXML
    AnchorPane box_large_two;

    private DashboardController dashboardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            // total user
            FXMLLoader smallItemHolderUser = new FXMLLoader(getClass().getResource(GUIPath.ReportItemHolder.getName()));
            ReportItemHolderController userReportItemHolderController = new ReportItemHolderController("user");
            smallItemHolderUser.setController(userReportItemHolderController);
            this.box_small_one.getChildren().add(smallItemHolderUser.load());

            // total feedback
            FXMLLoader smallItemHolderFeedback = new FXMLLoader(getClass().getResource(GUIPath.ReportItemHolder.getName()));
            ReportItemHolderController feedbackReportItemHolderController = new ReportItemHolderController("feedback");
            smallItemHolderFeedback.setController(feedbackReportItemHolderController);
            this.box_small_two.getChildren().add(smallItemHolderFeedback.load());

            // total delivered
            FXMLLoader smallItemHolderDelivered = new FXMLLoader(getClass().getResource(GUIPath.ReportItemHolder.getName()));
            ReportItemHolderController deliveredReportItemHolderController = new ReportItemHolderController("delivered");
            smallItemHolderDelivered.setController(deliveredReportItemHolderController);
            this.box_small_three.getChildren().add(smallItemHolderDelivered.load());

            // total cancelled
            FXMLLoader smallItemHolderCancelled = new FXMLLoader(getClass().getResource(GUIPath.ReportItemHolder.getName()));
            ReportItemHolderController cancelledReportItemHolderController = new ReportItemHolderController("cancelled");
            smallItemHolderCancelled.setController(cancelledReportItemHolderController);
            this.box_small_four.getChildren().add(smallItemHolderCancelled.load());

            //Large Box button views
            FXMLLoader largeItemHolderOne = new FXMLLoader(getClass().getResource(GUIPath.ReportLargeItemHolderButtonBar.getName()));
            ReportLargeItemHolderButtonBarController RLIHBBC = new ReportLargeItemHolderButtonBarController();

            this.box_large_one.getChildren().add(largeItemHolderOne.load());

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
