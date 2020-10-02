package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {
    @FXML
    AnchorPane mainPane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        try{
            mainPane.getChildren().add((Node)FXMLLoader.load(getClass().getResource("/fxml/Sidebar.fxml")));
            mainPane.getChildren().add((Node)FXMLLoader.load(getClass().getResource("/fxml/Topbar.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
