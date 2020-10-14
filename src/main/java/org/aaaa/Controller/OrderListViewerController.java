package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.FileHandler;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.GUIPath;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class OrderListViewerController extends ListViewerController implements Initializable {
    @FXML
    ScrollPane content_pane;
    @FXML
    VBox content;

    private FileHandler fileHandler;
    private DashboardController dashboardController;
    private OrderMainController orderMainController;

    public OrderListViewerController() {
        try {
            fileHandler = new FileHandler(DatabasePath.Order.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // get list of orders
        this.ui_title_button.setOnMouseClicked(e -> {
            try {
                FXMLLoader order = new FXMLLoader(getClass().getResource(GUIPath.OrderForm.getName()));
                OrderFormController orderFormController = new OrderFormController();
                order.setController(orderFormController);
                this.dashboardController.overridePage((Node) order.load());
            } catch (Exception err) {
                err.printStackTrace();
            }
        });
    }

    public void populateOrders(String type) {
        try{
            List<List<String>> temp = fileHandler.getContent(19);
            FXMLLoader loader = new FXMLLoader();
            // System.out.println(temp.get(0));
            for (int i = 0; i < temp.size(); i++) {
                switch (type) {
                    case "short":
                        loader = new FXMLLoader(getClass().getResource(GUIPath.OrderItemHolderShort.getName()));
                        break;
                    case "long": 
                        loader = new FXMLLoader(getClass().getResource(GUIPath.OrderItemHolderLong.getName()));
                        break;
                }
                OrderItemHolderController orderItemHolderController = new OrderItemHolderController(temp.get(i), type);
                orderItemHolderController.setDashboardController(this.dashboardController);
                loader.setController(orderItemHolderController);
                
                content.getChildren().add((Node)loader.load());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public OrderMainController getOrderMainController() {
        return orderMainController;
    }

    public void setOrderMainController(OrderMainController orderMainController) {
        this.orderMainController = orderMainController;
    }
}


