package org.aaaa;

import org.aaaa.Controller.DashboardController;
import org.aaaa.Controller.LoginController;
import org.aaaa.Enums.GUIPath;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
    // main removed from here as it caused problems using maven
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        stage = new Stage();
        this.stage = stage;

        startApp();

        // windows settings
        stage.showAndWait();
    }

    private void startApp() {
        try{
            // login 
            FXMLLoader login = new FXMLLoader(getClass().getResource(GUIPath.Login.getName()));
            LoginController loginController = new LoginController();
            login.setController(loginController);

            // dashboard
            FXMLLoader dashboard = new FXMLLoader(getClass().getResource(GUIPath.Dashboard.getName()));
            DashboardController dashboardController = new DashboardController();
            dashboard.setController(dashboardController);

            root = login.load();
            scene = new Scene(root);

            this.stage.setMinWidth(900);
            this.stage.setMinHeight(600);
            this.stage.setMaximized(false);
            this.stage.setTitle("Courier Service System - Login");
            this.stage.setScene(scene);

            loginController.getAuthenticated().addListener((v, oldValue, newValue) -> {
                if (newValue) {
                    try{
                        root = dashboard.load();
                        scene = new Scene(root);
                        this.stage.setScene(scene);
                        this.stage.setTitle("Courier Service System");
                        this.stage.setMinWidth(1366);
                        this.stage.setMinHeight(768);
                        this.stage.setMaximized(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            dashboardController.getLogoutProperty().addListener((v, oldValue, newValue) -> {
                if (newValue) {
                    this.startApp();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
