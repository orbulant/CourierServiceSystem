package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class DeliverySearchbarController implements Initializable {
    private DeliveryListViewerController deliveryListViewerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
 
    }

    public DeliveryListViewerController getDeliveryListViewerController() {
        return deliveryListViewerController;
    }

    public void setDeliveryListViewerController(DeliveryListViewerController deliveryListViewerController) {
        this.deliveryListViewerController = deliveryListViewerController;
    }
}
