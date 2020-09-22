package org.aaaa;

import javafx.application.Application;
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
        DashboardController dashboardController = new DashboardController();
        stage = new Stage();
        
        stage.setTitle("Sample");
        stage.setScene(dashboardController.getScene());

        // windows settings
        stage.setMinWidth(1366);
        stage.setMinHeight(768);
        stage.setMaximized(true);

        stage.show();
    }
    
}
