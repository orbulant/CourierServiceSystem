package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Enums.GUIPath;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OrderItemHolderController implements Initializable, BaseControllerInterface {
    @FXML
    Label status;
    @FXML
    Label order_id_name;
    @FXML
    Label order_date;
    @FXML
    Label delivery_date;
    @FXML
    Button auto_assign;
    @FXML
    Button edit_view;
    @FXML
    VBox short_content_container;
    @FXML
    HBox long_content_container;

    private String type;
    private List<String> data;
    private DashboardController dashboardController;

    public OrderItemHolderController(List<String> data, String type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        switch (type) {
            case "short":
                order_id_name.setText(this.getFormattedOrderIDName());
                order_date.setText(this.getFormattedOrderDate());
                delivery_date.setText(this.getFormattedDeliveryDate());
                status.setText(data.get(6));
                break;
            case "long":
                order_id_name.setText(this.getFormattedOrderIDName());
                order_date.setText(data.get(2));
                delivery_date.setText(data.get(3));
                status.setText(data.get(6));

                auto_assign.setVisible(true);
                edit_view.setVisible(true);
                break;
        }

        edit_view.setOnMouseClicked(e -> {
            try{
                FXMLLoader orderList = new FXMLLoader(getClass().getResource(GUIPath.OrderForm.getName()));
                // set custom controller to order
                OrderFormController orderFormController = new OrderFormController(data);
                orderFormController.setDashboardController(dashboardController);
                orderList.setController(orderFormController);
                this.dashboardController.overridePage((Node) orderList.load());
            } catch (Exception err) {
                err.printStackTrace();
            }
        });
    }

    public String getFormattedOrderIDName() {
        return "#Order" + data.get(0) + "/" + data.get(1);
    }

    public String getFormattedOrderDate() {
        return "Ordered On: " + data.get(2);
    }

    public String getFormattedDeliveryDate() {
        if(data.get(3).isBlank()) {
            return "Deliver By: ASAP";
        } else {
            return "Deliver By: " + data.get(3);
        }
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public DashboardController getDashboardController() {
        return this.dashboardController;
    }

    @Override
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
