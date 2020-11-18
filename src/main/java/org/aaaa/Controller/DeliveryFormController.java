package org.aaaa.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.aaaa.DeliveryCancellation;
import org.aaaa.Feedback;
import org.aaaa.Order;
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

    public void populateFields(Order order) {
        ui_label_one.setText(order.getAccount().getName());
        ui_label_two.setText(order.getAccount().getContactNum());
        ui_label_three.setText(order.getAddress().toString());
        ui_label_four.setText(order.getStatus());

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
                // put into delivery
                DeliveryCancellation deliveryCancellation = new DeliveryCancellation(order.getOrderID(), newValue, Status.Cancelling.getStatus());
                try{
                    // add delivery cancellation record
                    FileHandlerDelivery fileHandlerDelivery = new FileHandlerDelivery(DatabasePath.DeliveryCancellation.getName());
                    fileHandlerDelivery.addContent(fileHandlerDelivery.getContent(DatabasePath.DeliveryCancellation.getDataLength()), deliveryCancellation.get());
                    // change order status
                    FileHandlerOrder fileHandlerOrder = new FileHandlerOrder(DatabasePath.Order.getName());
                    order.setStatus(Status.Cancelling.getStatus());
                    fileHandlerOrder.update(fileHandlerOrder.getContent(DatabasePath.Order.getDataLength()), order.get());

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
                // change order status to delivered
                try{
                    order.setStatus(Status.Delivered.getStatus());
                    FileHandlerOrder fileHandlerOrder = new FileHandlerOrder(DatabasePath.Order.getName());
                    fileHandlerOrder.update(fileHandlerOrder.getContent(DatabasePath.Order.getDataLength()), order.get());

                    Feedback feedback = new Feedback();
                    feedback.setFeedback(newValue.replace("\n", " ").replace("\r", " "));
                    feedback.setOrder(order);
                    feedback.setFeedbackDate(LocalDate.now());
                    feedback.create();

                    // add feedback listener
                    this.deliveryListViewerController.populateDeliveries("", null);
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
