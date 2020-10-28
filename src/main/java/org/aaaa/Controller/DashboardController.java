package org.aaaa.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.aaaa.CurrentUser;
import org.aaaa.Enums.GUIPath;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class  DashboardController implements Initializable {
    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane contentPane;
    @FXML
    AnchorPane ui_pane_large_tall;
    @FXML
    AnchorPane ui_pane_small_two;
    @FXML
    AnchorPane ui_pane_large_thick;
    @FXML
    GridPane dashboardPane;
    @FXML
    Label mainTitle;
    @FXML
    Button sidebarButtonOne;
    @FXML
    Button sidebarButtonTwo;
    @FXML
    Button sidebarButtonThree;
    @FXML
    Button sidebarButtonFour;
    @FXML
    Button sidebarButtonFive;
    @FXML
    Button sidebarButtonSix;
    @FXML
    Button btn_view_Profile;

    private String title;
    private Node previousPage;
    private FXMLLoader orderMain;
    private FXMLLoader reportMain;
    private FXMLLoader feedbackMain;
    private FXMLLoader deliveryMain;
    private FXMLLoader AUG;
    private BooleanProperty logoutProperty = new SimpleBooleanProperty();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.setTitle(GUIPath.Dashboard.toString());
        this.pageLoader();      
    }

    private void pageLoader() {
        // load content
        this.loadDashboardContent();

        if (CurrentUser.getStaff().getRole().equals("managing")) {
            // load managing
            this.loadManagingContent();
        } else if (CurrentUser.getStaff().getRole().equals("delivery")) {
            // load delivery
            this.loadDeliveryContent();
        } 
    }

    // load common content
    private void loadDashboardContent() {
        try{
            // recent login
            FXMLLoader recentLogin = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to recent login
            RecentLoginListViewerController recentLoginController= new RecentLoginListViewerController();
            recentLogin.setController(recentLoginController);
            // add to dashboard pane
            ui_pane_small_two.getChildren().clear();
            ui_pane_small_two.getChildren().add((Node) recentLogin.load());

            recentLoginController.setTitle("Recent Login(s)");
            recentLoginController.setTitleButtonVisibility(false);
            
            // Sidebar Buttons
            sidebarButtonOne.setOnMouseClicked(e -> {
                this.setTitle(GUIPath.Dashboard.toString());
                this.overridePage(dashboardPane);
            });

            sidebarButtonSix.setOnMouseClicked(e -> {
                // logout
                this.logoutProperty.set(true);
            });

            //Top Profile button
            btn_view_Profile.setOnMouseClicked(e -> {
                try {
                    AUG = new FXMLLoader(getClass().getResource(GUIPath.Profile.getName()));
                    this.overridePage(AUG.load());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // load managing content and buttons
    public void loadManagingContent() {
        try{
            // order list
            FXMLLoader orderList = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to order
            OrderListViewerController orderListController = new OrderListViewerController("short");
            orderListController.setDashboardController(this);
            orderList.setController(orderListController);
            // order list
            FXMLLoader deliveryRequestList = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to order
            DeliveryCancellationListViewerController deliveryCancellationListController = new DeliveryCancellationListViewerController();
            deliveryCancellationListController.setDashboardController(this);
            deliveryRequestList.setController(deliveryCancellationListController);

            // add to dashboard pane
            ui_pane_large_tall.getChildren().clear();
            ui_pane_large_tall.getChildren().add((Node) orderList.load());
            orderListController.setTitle("Recent Orders");

            ui_pane_large_thick.getChildren().clear();
            ui_pane_large_thick.getChildren().add((Node) deliveryRequestList.load());
            deliveryCancellationListController.setTitle("Pending Delivery Cancellations");
            deliveryCancellationListController.setTitleButtonVisibility(false);

            // set delay to loadables using scroll pane to avoid error
            Platform.runLater(() -> {
                orderListController.populateOrders("");
                deliveryCancellationListController.populateCancellations();
            });

            this.sidebarButtonTwo.setText("Accounts");
            sidebarButtonTwo.setOnMouseClicked( e -> {
                this.setTitle(this.sidebarButtonTwo.getText());
                try {
                    AUG = new FXMLLoader(getClass().getResource(GUIPath.AccountUserGateway.getName()));
                    this.overridePage(AUG.load());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });



            this.sidebarButtonThree.setText("Order");
            sidebarButtonThree.setOnMouseClicked(e -> {
                this.setTitle(this.sidebarButtonThree.getText());
                try{
                    orderMain = new FXMLLoader(getClass().getResource(GUIPath.OrderMain.getName()));
                    // set custom controller to order
                    OrderMainController orderMainController = new OrderMainController();
                    orderMainController.setDashboardController(this);
                    orderMain.setController(orderMainController);
                    this.overridePage((Node) orderMain.load());
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });

            this.sidebarButtonFour.setText("Feedback");
            sidebarButtonFour.setOnMouseClicked(e -> {
                this.setTitle(this.sidebarButtonFour.getText());
                try{
                    feedbackMain = new FXMLLoader(getClass().getResource(GUIPath.FeedbackMain.getName()));
                    // set custom controller to order
                    FeedbackMainController feedbackMainController = new FeedbackMainController();
                    feedbackMainController.setDashboardController(this);
                    feedbackMain.setController(feedbackMainController);
                    this.overridePage((Node) feedbackMain.load());
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });
    
            this.sidebarButtonFive.setText("Report");
            sidebarButtonFive.setOnMouseClicked(e -> {
                this.setTitle(this.sidebarButtonFive.getText());
                try{
                    reportMain = new FXMLLoader(getClass().getResource(GUIPath.ReportMain.getName()));
                    // set custom controller to order
                    ReportMainController reportMainController = new ReportMainController();
                    reportMainController.setDashboardController(this);
                    reportMain.setController(reportMainController);
                    this.overridePage((Node) reportMain.load());
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // load delivery content and buttons
    public void loadDeliveryContent() {
        try{
            // delivery list
            FXMLLoader deliveryList = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to delivery
            DeliveryListViewerController deliveryListController = new DeliveryListViewerController();
            deliveryList.setController(deliveryListController);
            
            // add to dashboard pane
            ui_pane_large_tall.getChildren().clear();
            ui_pane_large_tall.getChildren().add((Node) deliveryList.load());

            deliveryListController.setTitle("Deliveries");
            deliveryListController.setTitleButtonVisibility(false);
            deliveryListController.setDashboard(true);

            // set delay to loadables using scroll pane to avoid error
            Platform.runLater(() -> {
                deliveryListController.populateDeliveries("", null);
            });

            this.sidebarButtonThree.setText("Delivery");
            sidebarButtonThree.setOnMouseClicked(e -> {
                this.setTitle(this.sidebarButtonThree.getText());
                try{
                    deliveryMain = new FXMLLoader(getClass().getResource(GUIPath.DeliveryMain.getName()));
                    // set custom controller to order
                    DeliveryMainController deliveryMainController = new DeliveryMainController();
                    deliveryMainController.setDashboardController(this);
                    deliveryMain.setController(deliveryMainController);
                    this.overridePage((Node) deliveryMain.load());
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });
            
            this.sidebarButtonFour.setVisible(false);
            this.sidebarButtonFive.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void overridePage(Node value) {
        if(!contentPane.getChildren().contains(value)) {
            this.previousPage = contentPane.getChildren().get(0);
            this.pageLoader();
            
            contentPane.getChildren().remove(0);
            contentPane.getChildren().add(value);
        }
    }

    public void loadPreviousPage() {
        this.overridePage(previousPage);
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        mainTitle.setText(this.title);
    }

    public BooleanProperty getLogoutProperty() {
        return logoutProperty;
    }

    public void setLogoutProperty(BooleanProperty logoutProperty) {
        this.logoutProperty = logoutProperty;
    }
}
