package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Address;

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
    private List<String> data;
    private DeliveryFormController deliveryFormController;

    public DeliveryItemHolderController(List<String> data, boolean isDashboard) {
        this.data = data;
        this.isDashboard = isDashboard;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Address address = new Address(new String[]{data.get(10),data.get(11),data.get(12),data.get(13),data.get(14)});
        order_name.setText(data.get(8));
        order_contact.setText(data.get(9));
        order_address.setText(address.toString());

        if (isDashboard) {
            this.edit_view.setVisible(false);
        } else {
            edit_view.setOnMouseClicked(e -> {
                try{
                    // pass info to delivery form
                    deliveryFormController.populateFields(data);
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
