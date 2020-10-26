package org.aaaa.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Staff;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandlerOrder;
import org.aaaa.FileHandlers.FileHandlerUser;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeliveryCancellationItemHolderController implements Initializable {
    @FXML
    Label lbl_one;
    @FXML
    Label lbl_two;
    @FXML
    Label lbl_three;
    @FXML
    Label lbl_four;
    @FXML
    Button btn_one;
    @FXML
    Button btn_two;

    private List<String> data;
    private DeliveryCancellationListViewerController deliveryCancellationListViewerController;

    public DeliveryCancellationItemHolderController(List<String> data) {
        this.data = data;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileHandlerOrder fileHandlerOrder = new FileHandlerOrder(DatabasePath.Order.getName());
        List<String> orderTemp = fileHandlerOrder.getOrderByID(data.get(0));
        lbl_one.setText(String.join("/", orderTemp.get(0), orderTemp.get(1)));

        FileHandlerUser fileHandlerUser = new FileHandlerUser(DatabasePath.Staff.getName());
        Staff deliveryStaff = fileHandlerUser.getUserByAccountID(data.get(1));
        lbl_two.setText(String.join("/", deliveryStaff.getAccountID(), deliveryStaff.getUsername()));

        lbl_three.setText(data.get(2));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        lbl_four.setText(LocalDateTime.parse(data.get(4)).format(formatter));

        btn_one.setOnMouseClicked(e -> {
            // change order status
        });

        btn_two.setOnMouseClicked(e -> {
            // assign to new deliverer
        });
    }

    public DeliveryCancellationListViewerController getDeliveryCancellationListViewerController() {
        return deliveryCancellationListViewerController;
    }

    public void setDeliveryCancellationListViewerController(DeliveryCancellationListViewerController deliveryCancellationListViewerController) {
        this.deliveryCancellationListViewerController = deliveryCancellationListViewerController;
    }
}
