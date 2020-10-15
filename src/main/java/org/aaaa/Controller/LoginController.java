package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
    @FXML
    TextField txt_username;
    @FXML
    TextField txt_password;
    @FXML
    Label lbl_err_message;
    @FXML
    Button btn_view_password;
    @FXML
    Button btn_submit;

    private BooleanProperty authenticated = new SimpleBooleanProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_submit.setOnMouseClicked(e -> {
            // run some authentication code here
            // use txt.getText() to get input in text field
            // use txt.setText() to set text in label/text field

            // run this if authentication success
            this.authenticated.setValue(true);;
        });
    }

    public BooleanProperty getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(BooleanProperty authenticated) {
        this.authenticated = authenticated;
    }
}
