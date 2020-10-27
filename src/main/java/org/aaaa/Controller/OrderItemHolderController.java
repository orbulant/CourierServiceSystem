package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.Order;
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
    private Label status;
    @FXML
    private Label order_id_name;
    @FXML
    private Label order_date;
    @FXML
    private Label delivery_date;
    @FXML
    private Button edit_view;
    @FXML
    VBox short_content_container;
    @FXML
    HBox long_content_container;

    private String type;
    private Order order;
    private DashboardController dashboardController;

    public OrderItemHolderController(Order order, String type) {
        this.order = order;
        this.type = type;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(order != null) {
            switch (type) {
                case "short":
                    order_id_name.setText(this.getFormattedOrderIDName());
                    order_date.setText(this.getFormattedOrderDate());
                    status.setText(order.getStatus());
    
                    if(order.getDeliDate() != null) {
                        delivery_date.setText(this.getFormattedDeliveryDate());
                    }
                    
                    break;
                case "long":
                    order_id_name.setText(this.getFormattedOrderIDName());
                    order_date.setText(order.getOrderDate().toString());
                    status.setText(order.getStatus());
    
                    if(order.getDeliDate() != null) {
                        delivery_date.setText(order.getDeliDate().toString());
                    }
    
                    edit_view.setVisible(true);
                    break;
            }
    
            edit_view.setOnMouseClicked(e -> {
                try{
                    FXMLLoader orderList = new FXMLLoader(getClass().getResource(GUIPath.OrderForm.getName()));
                    // set custom controller to order
                    OrderFormController orderFormController = new OrderFormController(order);
                    orderFormController.setDashboardController(dashboardController);
                    orderList.setController(orderFormController);
                    this.dashboardController.overridePage((Node) orderList.load());
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });
        }
    }

    public String getFormattedOrderIDName() {
        return "#Order" + order.getOrderID() + "/" + order.getOrderName();
        // return "#Order" + data.get(0) + "/" + data.get(1);
    }

    public String getFormattedOrderDate() {
        return "Ordered On: " + order.getOrderDate().toString();
        // return "Ordered On: " + data.get(2);
    }

    public String getFormattedDeliveryDate() {
        if(order.getDeliDate() == null) {
            return "Deliver By: ASAP";
        } else {
            return "Deliver By: " + order.getDeliDate().toString();
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    

    @Override
    public DashboardController getDashboardController() {
        return this.dashboardController;
    }

    @Override
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public Label getStatus() {
        return status;
    }

    public void setStatus(Label status) {
        this.status = status;
    }

    public Label getOrderIdName() {
        return order_id_name;
    }

    public void setOrderIdName(Label order_id_name) {
        this.order_id_name = order_id_name;
    }

    public Label getOrderDate() {
        return order_date;
    }

    public void setOrderDate(Label order_date) {
        this.order_date = order_date;
    }

    public Label getDeliveryDate() {
        return delivery_date;
    }

    public void setDeliveryDate(Label delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Button getEditView() {
        return edit_view;
    }

    public void setEditView(Button edit_view) {
        this.edit_view = edit_view;
    }
}
