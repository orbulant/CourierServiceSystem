package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.GUIPath;

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

    private OrderListViewerController orderListController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            // searchbar
            FXMLLoader searchbar = new FXMLLoader(getClass().getResource(GUIPath.Searchbar.getName()));
            // set custom controller to order
            // OrderListViewerController orderListController = new OrderListViewerController();
            // searchbar.setController(orderListController);
            ui_top_pane.getChildren().add((Node) searchbar.load());

            // order list
            FXMLLoader orderList = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to order
            orderListController = new OrderListViewerController();
            orderList.setController(orderListController);
    
            ui_bottom_pane.getChildren().add((Node) orderList.load());
            orderListController.setTitle("Recent Orders");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderListViewerController getOrderListController() {
        return orderListController;
    }
}
