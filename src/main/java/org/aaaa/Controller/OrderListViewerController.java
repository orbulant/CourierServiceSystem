package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.FileHandlers.FileHandler;
import org.aaaa.Enums.DatabasePath;

import javafx.fxml.Initializable;

public class OrderListViewerController extends ListViewerController implements Initializable {
    FileHandler fileHandler;

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

    }

    public List<List<String>> populateOrders() {
        System.out.println(fileHandler.getContent(17));
        return fileHandler.getContent(17);
    }
}


