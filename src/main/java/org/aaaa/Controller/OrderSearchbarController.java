package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class OrderSearchbarController extends SearchbarController implements Initializable {
    private OrderListViewerController orderListViewerController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search_text_field.setOnAction(e -> {
            search_text_field.setPromptText("");
        });

        search_text_field.setOnKeyPressed(e -> {
            System.out.println(e);
            orderListViewerController.populateOrders(this.search_text_field.getText());
        });
    }

    public OrderListViewerController getOrderListViewerController() {
        return orderListViewerController;
    }

    public void setOrderListViewerController(OrderListViewerController orderListViewerController) {
        this.orderListViewerController = orderListViewerController;
    }
}
