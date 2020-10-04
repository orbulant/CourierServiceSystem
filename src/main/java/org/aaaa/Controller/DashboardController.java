package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class DashboardController implements Initializable {
    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane contentPane;
    @FXML
    AnchorPane ui_pane_large_tall;
    @FXML
    GridPane dashboardPane;
    @FXML
    Label mainTitle;
    @FXML
    Button sidebarOrderButton;
    @FXML
    Button sidebarHomeButton;

    private String title;
    private Node order;
    private Node orderMain;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.setTitle("Dashboard");
        // load content
        this.loadDashboardContent();

        try{
            if(order == null) {
                order = (Node)FXMLLoader.load(getClass().getResource("/fxml/OrderForm.fxml"));
            } 

            if(orderMain == null) {
                orderMain = (Node)FXMLLoader.load(getClass().getResource("/fxml/OrderMain.fxml"));
            } 
        } catch (Exception eButton) {
            eButton.printStackTrace();
        }

        // Sidebar Buttons
        sidebarHomeButton.setOnMouseClicked(e -> {
            this.overridePage(dashboardPane);
        });

        sidebarOrderButton.setOnMouseClicked(e -> {
            this.overridePage(orderMain);
        });
    }

    private void loadDashboardContent() {
        try{
            // order list
            FXMLLoader orderList = new FXMLLoader(getClass().getResource("/fxml/ListViewer.fxml"));
            // set custom controller to order
            OrderListViewerController orderListController = new OrderListViewerController();
            orderList.setController(orderListController);
    
            ui_pane_large_tall.getChildren().add((Node) orderList.load());
            orderListController.setTitle("Recent Orders");
            orderListController.getTitleButton().setOnMouseClicked(e -> {
                this.overridePage(order);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void overridePage(Node value) {
        if(!contentPane.getChildren().contains(value)) {
            contentPane.getChildren().remove(0);
            contentPane.getChildren().add(value);
        }
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        mainTitle.setText(this.title);
    }
}
