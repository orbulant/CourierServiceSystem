package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import org.aaaa.CurrentUser;
import org.aaaa.RecentLogin;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandlerUser;

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
            //START OF LAMBDA EXPRESSION
            // run some authentication code here
            // use txt.getText() to get input in text field
            // use txt.setText() to set text in label/text field
            String loginfield = txt_username.getText();
            String passwordfield = txt_password.getText();
            FileHandlerUser fh = new FileHandlerUser(DatabasePath.Staff.getName());

            int i = 0;
            while (i < fh.getContent(DatabasePath.Staff.getDataLength()).size()) {
                if (loginfield.equals(fh.getContent(DatabasePath.Staff.getDataLength()).get(i).get(0)) && passwordfield.equals(fh.getContent(DatabasePath.Staff.getDataLength()).get(i).get(1))) {
                    CurrentUser.setStaff(fh.assignUser(fh.getContent(DatabasePath.Staff.getDataLength()).get(i)));

                    RecentLogin recentLogin = new RecentLogin();
                    // create a recent login record
                    recentLogin.create();

                    // run this if authentication success
                    this.authenticated.setValue(true);
                }
                i++;
            }
            lbl_err_message.setText("Invalid credentials!");
            lbl_err_message.setVisible(true);
            // //END OF LAMBDA EXPRESSION
        })
    ;}

    public BooleanProperty getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(BooleanProperty authenticated) {
        this.authenticated = authenticated;
    }
}
