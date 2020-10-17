package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.FileHandlers.FileHandler;
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

    private String type;
    private FileHandler fileHandler;
    private DashboardController dashboardController;

    public OrderListViewerController(String type) {
        try {
            this.type = type;
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

    public void populateOrders(String filter) {
        try{
            content.getChildren().clear();
            List<List<String>> temp = fileHandler.getContent(DatabasePath.Order.getDataLength());
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

                if(temp.get(i).toString().contains(filter)) {
                    OrderItemHolderController orderItemHolderController = new OrderItemHolderController(temp.get(i), type);
                    orderItemHolderController.setDashboardController(this.dashboardController);
                    loader.setController(orderItemHolderController);
                    content.getChildren().add((Node)loader.load());
                }
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
}


