package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandlerDelivery;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class DeliveryCancellationListViewerController extends ListViewerController implements Initializable {
    @FXML
    ScrollPane content_pane;
    @FXML
    VBox content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populateCancellations() {
        FileHandlerDelivery fileHandlerDelivery = new FileHandlerDelivery(DatabasePath.DeliveryCancellation.getName());
        List<List<String>> temp = fileHandlerDelivery.getUnapprovedCancellations(); 
        System.out.println(temp);
    }
}
