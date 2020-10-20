package org.aaaa;

import org.aaaa.Controller.LoginController;
import org.aaaa.Enums.GUIPath;

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
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws Exception {
        stage = new Stage();
        this.stage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(GUIPath.Login.getName()));
        LoginController loginController = new LoginController();
        loader.setController(loginController);
        root = loader.load();
        scene = new Scene(root);
        
        this.stage.setMinWidth(900);
        this.stage.setMinHeight(600);
        this.stage.setTitle("Courier Service System - Login");
        this.stage.setScene(scene);

        loginController.getAuthenticated().addListener((v, oldValue, newValue) -> {
            if (newValue) {
                try{
                    root = FXMLLoader.load(getClass().getResource(GUIPath.Dashboard.getName()));
                    scene = new Scene(root);
                    this.stage.setScene(scene);
                    // windows settings
                    this.stage.setMinWidth(1366);
                    this.stage.setMinHeight(768);
                    this.stage.setMaximized(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // windows settings
        stage.showAndWait();
    }
    
}
