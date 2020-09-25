package org.aaaa.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DashboardController {
    private Scene scene;
    private Parent root;

    public Scene getScene() {
        try{
            root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        scene = new Scene(root);

        return this.scene;
    }
}
