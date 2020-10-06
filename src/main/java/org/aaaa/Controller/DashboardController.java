package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.GUIPath;

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
    AnchorPane ui_pane_small_two;
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
    private Node previousPage;
    private FXMLLoader orderMainLoader;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.setTitle(GUIPath.Dashboard.toString());
        // load content
        this.loadDashboardContent();

        try{
            if(order == null) {
                order = (Node)FXMLLoader.load(getClass().getResource(GUIPath.OrderForm.getName()));
            } 

            if(orderMain == null) {
                orderMainLoader = new FXMLLoader();
                orderMain = orderMainLoader.load(getClass().getResource(GUIPath.OrderMain.getName()).openStream());
            } 
        } catch (Exception eButton) {
            eButton.printStackTrace();
        }

        // Sidebar Buttons
        sidebarHomeButton.setOnMouseClicked(e -> {
            this.setTitle(GUIPath.Dashboard.toString());

            this.overridePage(dashboardPane);
        });

        sidebarOrderButton.setOnMouseClicked(e -> {
            this.setTitle(this.sidebarOrderButton.getText());

            OrderMainController orderMainController = orderMainLoader.getController();
            orderMainController.getOrderListController().getTitleButton().setOnMouseClicked(e1 -> {
                this.overridePage(order);
            });
            this.overridePage(orderMain);
        });
    }

    private void loadDashboardContent() {
        try{
            // order list
            FXMLLoader orderList = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to order
            OrderListViewerController orderListController = new OrderListViewerController();
            orderList.setController(orderListController);
    
            // recent login
            FXMLLoader recentLogin = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to recent login
            RecentLoginListViewerController recentLoginController= new RecentLoginListViewerController();
            recentLogin.setController(recentLoginController);

            // adding to pane
            ui_pane_large_tall.getChildren().add((Node) orderList.load());
            orderListController.setTitle("Recent Orders");
            orderListController.getTitleButton().setOnMouseClicked(e -> {
                this.overridePage(order);
            });

            ui_pane_small_two.getChildren().add((Node) recentLogin.load());
            recentLoginController.setTitle("Recent Login(s)");
            recentLoginController.setTitleButtonVisibility(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void overridePage(Node value) {
        if(!contentPane.getChildren().contains(value)) {
            contentPane.getChildren().remove(0);
            contentPane.getChildren().add(value);

            this.previousPage = value;
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
}
