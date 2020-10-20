package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.Enums.GUIPath;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

public class DeliveryMainController implements Initializable, BaseControllerInterface {
    @FXML
    AnchorPane ui_tall_pane_one;
    @FXML
    AnchorPane ui_tall_pane_two;
    @FXML
    AnchorPane ui_tall_pane_three;
    @FXML
    DatePicker date_filter;

    private DashboardController dashboardController;
    private DeliveryListViewerController deliveryListController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            // delivery list
            FXMLLoader deliveryList = new FXMLLoader(getClass().getResource(GUIPath.ListViewer.getName()));
            // set custom controller to delivery
            deliveryListController = new DeliveryListViewerController();
            deliveryList.setController(deliveryListController);
    
            // searchbar
            FXMLLoader searchbar = new FXMLLoader(getClass().getResource(GUIPath.Searchbar.getName()));
            // set custom controller to searchbar
            DeliverySearchbarController deliverySearchbarController = new DeliverySearchbarController();
            deliverySearchbarController.setDeliveryListViewerController(this.deliveryListController);
            searchbar.setController(deliverySearchbarController);

            // delivery form
            FXMLLoader deliveryForm = new FXMLLoader(getClass().getResource(GUIPath.DeliveryForm.getName()));
            // set custom controller to delivery
            DeliveryFormController deliveryFormController = new DeliveryFormController();
            deliveryForm.setController(deliveryFormController);

            // add to delivery main panes
            ui_tall_pane_one.getChildren().add((Node) searchbar.load());
            ui_tall_pane_two.getChildren().add((Node) deliveryList.load());
            ui_tall_pane_three.getChildren().add((Node) deliveryForm.load());

            deliveryListController.setTitle("Deliveries");
            deliveryListController.setTitleButtonVisibility(false);
            deliveryListController.setDeliveryFormController(deliveryFormController);

            Platform.runLater(() -> {
                deliveryListController.populateDeliveries("", date_filter.getValue());
            });

            date_filter.setOnAction(e -> {
                deliveryListController.populateDeliveries("", date_filter.getValue());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DashboardController getDashboardController() {
        return dashboardController;
    }

    @Override
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
