package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.GUIPath;
import org.aaaa.FileHandlers.FileHandlerDelivery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class DeliveryCancellationListViewerController extends ListViewerController implements Initializable {
    @FXML
    ScrollPane content_pane;
    @FXML
    VBox content;

    private FileHandlerDelivery fileHandlerDelivery;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populateCancellations() {
        try{
            content.getChildren().clear();
            fileHandlerDelivery = new FileHandlerDelivery(DatabasePath.DeliveryCancellation.getName());
            List<List<String>> temp = fileHandlerDelivery.getUnapprovedCancellations();
            FXMLLoader loader = new FXMLLoader();
            for (int i = 0; i < temp.size(); i++) {
                loader = new FXMLLoader(getClass().getResource(GUIPath.DeliveryCancellationItemHolder.getName()));
                DeliveryCancellationItemHolderController deliveryCancellationItemController = new DeliveryCancellationItemHolderController(temp.get(i));
                deliveryCancellationItemController.setDeliveryCancellationListViewerController(this);
                loader.setController(deliveryCancellationItemController);
                content.getChildren().add((Node)loader.load());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
