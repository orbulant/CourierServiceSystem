package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.Address;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeliveryFormController implements Initializable {
    @FXML
    Label ui_label_one;
    @FXML
    Label ui_label_two;
    @FXML
    Label ui_label_three;
    @FXML
    Label ui_label_four;
    @FXML
    Button ui_button_clear;
    @FXML
    Button ui_button_cancel;
    @FXML
    Button ui_button_delivered;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void populateFields(List<String> data) {
        Address address = new Address(new String[]{data.get(10),data.get(11),data.get(12),data.get(13),data.get(14)});
        ui_label_one.setText(data.get(8));
        ui_label_two.setText(data.get(9));
        ui_label_three.setText(address.toString());
        ui_label_four.setText(data.get(7));

        this.ui_button_cancel.setDisable(false);
        this.ui_button_delivered.setDisable(false);

        ui_button_cancel.setOnMouseClicked(e -> {
            // prompt cancellation box
        });

        ui_button_delivered.setOnMouseClicked(e -> {
            // prompt feedback box
        });

        ui_button_clear.setOnMouseClicked(e -> {
            ui_label_one.setText("-");
            ui_label_two.setText("-");
            ui_label_three.setText("-");
            ui_label_four.setText("-");

            this.ui_button_cancel.setDisable(true);
            this.ui_button_delivered.setDisable(true);
        });
    }
}
