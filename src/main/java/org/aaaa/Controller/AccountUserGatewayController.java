package org.aaaa.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.aaaa.Enums.GUIPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AccountUserGatewayController implements Initializable, BaseControllerInterface {

    @FXML
    Button btn_view_Account;
    @FXML
    Button btn_view_Users;

    private DashboardController dashboardController;

    public AccountUserGatewayController(){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_view_Account.setOnMouseClicked(e -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GUIPath.AccountOverview.getName()));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Viewing Accounts");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException exh) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new Window.", exh);
                }
            });

        btn_view_Users.setOnMouseClicked(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GUIPath.UserOverview.getName()));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Viewing Users");
                stage.setScene(scene);
                stage.show();
            } catch (IOException exh) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", exh);
            }
        });
    }

    @Override
    public DashboardController getDashboardController() {
        return this.dashboardController;
    }

    @Override
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }


}
