package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.Order;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeliveryItemHolderController implements Initializable {
    @FXML
    Label order_name;
    @FXML
    Label order_contact;
    @FXML
    Label order_address;
    @FXML
    Button edit_view;

    private boolean isDashboard;
    private Order order;
    private DeliveryFormController deliveryFormController;

    public DeliveryItemHolderController(Order order, boolean isDashboard) {
        this.order = order;
        this.isDashboard = isDashboard;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        order_name.setText(order.getAccount().getName());
        order_contact.setText(order.getAccount().getContactNum());
        order_address.setText(order.getAddress().toString());

        if (isDashboard) {
            this.edit_view.setVisible(false);
        } else {
            edit_view.setOnMouseClicked(e -> {
                try{
                    // pass info to delivery form
                    deliveryFormController.populateFields(order);
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });
        }
    }

    public DeliveryFormController getDeliveryFormController() {
        return deliveryFormController;
    }

    public void setDeliveryFormController(DeliveryFormController deliveryFormController) {
        this.deliveryFormController = deliveryFormController;
    }
}
