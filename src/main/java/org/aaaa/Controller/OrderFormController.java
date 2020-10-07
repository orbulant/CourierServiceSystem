package org.aaaa.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.aaaa.Person;
import org.aaaa.Address;
import org.aaaa.ErrorMessage;
import org.aaaa.Order;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OrderFormController implements Initializable {
    @FXML
    TextField txt_order_name;
    @FXML
    TextArea txt_order_desc;
    @FXML
    TextField txt_name;
    @FXML
    TextField txt_contact;
    @FXML
    TextField txt_address;
    @FXML
    TextField txt_city;
    @FXML
    TextField txt_postcode;
    @FXML
    TextField txt_state;
    @FXML
    TextField txt_country;
    @FXML
    ComboBox<String> txt_assign_to;
    @FXML
    DatePicker dp_order_date;
    @FXML
    DatePicker dp_deli_date;
    @FXML
    CheckBox cb_is_fragile;
    @FXML
    CheckBox cb_auto_assign;
    @FXML
    Label lbl_err_msg;
    @FXML
    Button btn_submit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_submit.setOnMouseClicked(e -> {
            this.processData();
        });
    }

    public void processData() {
        // check form validity
        if(this.isValid()) {
            // add to database
            Person person = new Person(txt_name.getText(), txt_contact.getText());
            Address address = new Address(new String[]{txt_address.getText(), txt_city.getText(), txt_postcode.getText(), txt_state.getText(), txt_country.getText()});
            Order order = new Order();
            order.setOrderName(txt_order_name.getText());
            order.setOrderDesc(txt_order_desc.getText());
            order.setOrderDate(dp_order_date.getValue());
            order.setDeliDate(dp_order_date.getValue());
            // order.setAssignTo();
            order.setIsFragile(cb_is_fragile.isSelected());
            order.setAutoAssign(cb_auto_assign.isSelected());
            order.setAccount(person);
            order.setAddress(address);
        } else {
            // prompt error message
            lbl_err_msg.setVisible(true);
            lbl_err_msg.setText(ErrorMessage.Mandatory.getMessage());
        }
    }

    public boolean isValid() {
        // check all required fields
        if (txt_order_name.getText().isBlank() ||
            txt_order_desc.getText().isBlank() ||
            txt_name.getText().isBlank() ||
            txt_contact.getText().isBlank() ||
            txt_address.getText().isBlank() ||
            txt_city.getText().isBlank() ||
            txt_postcode.getText().isBlank() ||
            txt_state.getText().isBlank() || 
            txt_country.getText().isBlank() ||
            dp_order_date.getValue() != null ||
            (cb_auto_assign.isSelected() && txt_assign_to.getValue() != null)) {
                return true;
            }
        
        return false;        
    }
}
