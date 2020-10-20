package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class DeliverySearchbarController extends SearchbarController implements Initializable {
    private DeliveryListViewerController deliveryListViewerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search_text_field.setOnAction(e -> {
            search_text_field.setPromptText("");
        });

        search_text_field.setOnKeyPressed(e -> {
            deliveryListViewerController.populateDeliveries(this.search_text_field.getText(), null);
        });
    }

    public DeliveryListViewerController getDeliveryListViewerController() {
        return deliveryListViewerController;
    }

    public void setDeliveryListViewerController(DeliveryListViewerController deliveryListViewerController) {
        this.deliveryListViewerController = deliveryListViewerController;
    }
}
