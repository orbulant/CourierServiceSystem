package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Address;
import org.aaaa.DeliveryCancellation;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Status;
import org.aaaa.FileHandlers.FileHandlerDelivery;
import org.aaaa.FileHandlers.FileHandlerOrder;
import org.aaaa.Popup.PopupBox;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeliveryFormController implements Initializable {
    @FXML
    Label ui_label_one;
    @FXML
    Label ui_label_two;
    @FXML
    Label ui_label_three;
    @FXML
    Label ui_label_four;
    @FXML
    Button ui_button_clear;
    @FXML
    Button ui_button_cancel;
    @FXML
    Button ui_button_delivered;

    private PopupBox popupBox;
    private DeliveryListViewerController deliveryListViewerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populateFields(List<String> data) {
        Address address = new Address(new String[]{data.get(10),data.get(11),data.get(12),data.get(13),data.get(14)});
        ui_label_one.setText(data.get(8));
        ui_label_two.setText(data.get(9));
        ui_label_three.setText(address.toString());
        ui_label_four.setText(data.get(7));

        ui_button_clear.setOnAction(e -> {
            ui_label_one.setText("-");
            ui_label_two.setText("-");
            ui_label_three.setText("-");
            ui_label_four.setText("-");

            this.ui_button_cancel.setDisable(true);
            this.ui_button_delivered.setDisable(true);
        });
        
        this.ui_button_cancel.setDisable(false);
        this.ui_button_delivered.setDisable(false);

        ui_button_cancel.setOnMouseClicked(e -> {
            // prompt cancellation box
            popupBox = new PopupBox(ui_button_cancel.getText(), "Please Provide Cancellation Reason");
            popupBox.getTextAreaProperty().addListener((v, oldValue, newValue) -> {
                System.out.println(newValue);
                // put into delivery
                DeliveryCancellation deliveryCancellation = new DeliveryCancellation(data.get(0), newValue, Status.Processing.getStatus());
                try{
                    FileHandlerDelivery fileHandlerDelivery = new FileHandlerDelivery(DatabasePath.DeliveryCancellation.getName());
                    fileHandlerDelivery.addContent(fileHandlerDelivery.getContent(DatabasePath.DeliveryCancellation.getDataLength()), deliveryCancellation.get());
                    this.getDeliveryListViewerController().populateDeliveries("", null);
                    this.ui_button_clear.fire();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });
            popupBox.showStage();
        });
        
        ui_button_delivered.setOnMouseClicked(e -> {
            // prompt feedback box
            popupBox = new PopupBox(ui_button_delivered.getText(), "Please Provide Customer Feedback");
            popupBox.getTextAreaProperty().addListener((v, oldValue, newValue) -> {
                System.out.println(newValue);
                data.set(7, Status.Delivered.getStatus());
                // change order status to delivered
                try{
                    FileHandlerOrder fileHandlerOrder = new FileHandlerOrder(DatabasePath.Order.getName());
                    fileHandlerOrder.update(fileHandlerOrder.getContent(DatabasePath.Order.getDataLength()), data);
                    this.getDeliveryListViewerController().populateDeliveries("", null);
                    this.ui_button_clear.fire();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });
            popupBox.showStage();
        });

        
    }

    public DeliveryListViewerController getDeliveryListViewerController() {
        return deliveryListViewerController;
    }

    public void setDeliveryListViewerController(DeliveryListViewerController deliveryListViewerController) {
        this.deliveryListViewerController = deliveryListViewerController;
    }
}
