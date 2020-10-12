package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class SidebarController implements Initializable {
    @FXML
    Button sidebarOrderButton;
    Button sidebarHomeButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        sidebarHomeButton.setOnMouseClicked(e -> {
            this.update("");
        });

        sidebarOrderButton.setOnMouseClicked(e -> {
            this.update("/fxml/OrderForm.fxml");
        });
    }

    public String update(String fxmlName) {
        return fxmlName;
    }
}
