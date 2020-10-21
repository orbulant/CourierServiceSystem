package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PopupBoxController implements Initializable {
    @FXML
    private Label lbl_message;
    @FXML
    private TextArea txt_area;
    @FXML
    private Button btn_one;
    @FXML
    private Button btn_two;

    private String message;
    private BooleanProperty cancelProperty;
    private BooleanProperty confirmProperty;

    public PopupBoxController(String message) {
        this.message = message;
        this.cancelProperty = new SimpleBooleanProperty();
        this.confirmProperty = new SimpleBooleanProperty();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lbl_message.setText(this.message);
        this.btn_one.setText("Cancel");
        this.btn_one.setOnAction(e -> {
            this.cancelProperty.set(true);
        });

        this.btn_two.setText("Confirm");
        this.btn_two.setOnAction(e -> {
            this.confirmProperty.set(true);
        });
    }

    public Label getMessageLabel() {
        return lbl_message;
    }

    public void setMessageLabel(Label lbl_message) {
        this.lbl_message = lbl_message;
    }

    public TextArea getTextArea() {
        return txt_area;
    }

    public void setTextArea(TextArea txt_area) {
        this.txt_area = txt_area;
    }

    public Button getButtonOne() {
        return btn_one;
    }

    public void setButtonOne(Button btn_one) {
        this.btn_one = btn_one;
    }

    public Button getButtonTwo() {
        return btn_two;
    }

    public void setButtonTwo(Button btn_two) {
        this.btn_two = btn_two;
    }

    public BooleanProperty getCancelProperty() {
        return cancelProperty;
    }

    public void setCancelProperty(BooleanProperty cancelProperty) {
        this.cancelProperty = cancelProperty;
    }

    public BooleanProperty getConfirmProperty() {
        return confirmProperty;
    }

    public void setConfirmProperty(BooleanProperty confirmProperty) {
        this.confirmProperty = confirmProperty;
    }
}
