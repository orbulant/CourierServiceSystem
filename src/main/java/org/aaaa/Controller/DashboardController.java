package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class DashboardController implements Initializable {
    @FXML
    AnchorPane mainPane;
    @FXML
    AnchorPane contentPane;
    @FXML
    GridPane dashboardPane;
    @FXML
    Button sidebarOrderButton;
    @FXML
    Button sidebarHomeButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            mainPane.getChildren().add((Node)FXMLLoader.load(getClass().getResource("/fxml/Topbar.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sidebarHomeButton.setOnMouseClicked(e -> {
            try{
                contentPane.getChildren().remove(0);
                contentPane.getChildren().add(dashboardPane);
            } catch (Exception eButton) {
                eButton.printStackTrace();
            }
        });

        sidebarOrderButton.setOnMouseClicked(e -> {
            try{
                contentPane.getChildren().remove(dashboardPane);
                contentPane.getChildren().add((Node)FXMLLoader.load(getClass().getResource("/fxml/OrderForm.fxml")));
            } catch (Exception eButton) {
                eButton.printStackTrace();
            }
        });
    }
}
