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

public class OrderMainController implements Initializable {
    @FXML
    AnchorPane ui_top_pane;
    @FXML
    AnchorPane ui_bottom_pane;

    private DashboardController dashboardController;
    private OrderListViewerController orderListController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            // order list
            FXMLLoader orderList = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to order
            orderListController = new OrderListViewerController("long");
            orderListController.setDashboardController(this.dashboardController);
            orderList.setController(orderListController);
    
            // searchbar
            FXMLLoader searchbar = new FXMLLoader(getClass().getResource(GUIPath.Searchbar.getName()));
            // set custom controller to searchbar
            OrderSearchbarController orderSearchbarController = new OrderSearchbarController();
            orderSearchbarController.setOrderListViewerController(this.orderListController);
            searchbar.setController(orderSearchbarController);

            ui_top_pane.getChildren().add((Node) searchbar.load());
            ui_bottom_pane.getChildren().add((Node) orderList.load());
            orderListController.setTitle("Recent Orders");

            Platform.runLater(() -> {
                orderListController.populateOrders("");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderListViewerController getOrderListController() {
        return orderListController;
    }

    public DashboardController getDashboardController(DashboardController dashboardController) {
        return this.dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
