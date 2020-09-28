package org.aaaa;

import org.aaaa.Controller.DashboardController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
    // main removed from here as it caused problems using maven

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        stage = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
        Scene scene = new Scene(root);
        
        stage.setTitle("Sample");
        stage.setScene(scene);
        // windows settings
        stage.setMinWidth(1366);
        stage.setMinHeight(768);
        stage.setMaximized(true);

        stage.show();
    }
    
}
