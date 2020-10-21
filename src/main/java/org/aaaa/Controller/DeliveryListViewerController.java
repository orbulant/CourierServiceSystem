package org.aaaa.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.GUIPath;
import org.aaaa.FileHandlers.FileHandlerOrder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class DeliveryListViewerController extends ListViewerController implements Initializable {
    @FXML
    ScrollPane content_pane;
    @FXML
    VBox content;

    private boolean isDashboard = false;
    private DeliveryFormController deliveryFormController;
    private FileHandlerOrder fileHandler;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            fileHandler = new FileHandlerOrder(DatabasePath.Order.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateDeliveries(String filter, LocalDate date) {
        try{
            content.getChildren().clear();
            List<List<String>> temp = fileHandler.getDelivererContent(DatabasePath.Order.getDataLength(), date);
            FXMLLoader loader = new FXMLLoader();
            for (int i = 0; i < temp.size(); i++) {
                loader = new FXMLLoader(getClass().getResource(GUIPath.DeliveryItemHolder.getName()));

                if(temp.get(i).toString().contains(filter)) {
                    // add item controller here
                    DeliveryItemHolderController deliveryItemHolderController = new DeliveryItemHolderController(temp.get(i), isDashboard);
                    deliveryItemHolderController.setDeliveryFormController(deliveryFormController);
                    loader.setController(deliveryItemHolderController);
                    content.getChildren().add((Node)loader.load());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DeliveryFormController getDeliveryFormController() {
        return deliveryFormController;
    }

    public void setDeliveryFormController(DeliveryFormController deliveryFormController) {
        this.deliveryFormController = deliveryFormController;
    }
    
    public boolean isDashboard() {
        return isDashboard;
    }

    public void setDashboard(boolean isDashboard) {
        this.isDashboard = isDashboard;
    }
}
