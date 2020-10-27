package org.aaaa.Controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.aaaa.RecentLogin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RecentLoginItemHolderController implements Initializable {
    @FXML
    Label txt_display;

    private RecentLogin recentLogin;

    public RecentLoginItemHolderController(RecentLogin recentLogin) {
        this.recentLogin = recentLogin;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        txt_display.setText(String.join("@", recentLogin.getStaff().getUsername(), recentLogin.getLoginDateTime().format(formatter)));
    }
}
