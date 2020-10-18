package org.aaaa.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.aaaa.Person;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Roles;
import org.aaaa.FileHandlers.FileHandlerAccount;
import org.aaaa.FileHandlers.FileHandlerOrder;
import org.aaaa.FileHandlers.FileHandlerUser;
import org.aaaa.Address;
import org.aaaa.Enums.ErrorMessage;
import org.aaaa.Order;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class OrderFormController implements Initializable {
    @FXML
    Label title_label;
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
    ComboBox<Person> txt_assign_to;
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

    private String title;
    private List<String> data;
    private DashboardController dashboardController;

    public OrderFormController() {
        this.title = "Create an Order";
    }

    public OrderFormController(List<String> data) {
        System.out.println(data);
        this.data = data;
        this.title = "Edit/View an Order";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.title_label.setText(this.title);

        // populate data if navigated from edit
        if (data != null) {
            this.txt_order_name.setText(data.get(1));
            this.txt_order_desc.setText(data.get(2));
            this.dp_order_date.setValue(LocalDate.parse(data.get(3)));
            this.dp_deli_date.setValue(data.get(4).length() > 0 ? LocalDate.parse(data.get(4)) : null);
            this.cb_is_fragile.setSelected(Boolean.parseBoolean(data.get(5)));
            this.txt_name.setText(data.get(8));
            this.txt_contact.setText(data.get(9));
            this.txt_address.setText(data.get(10));
            this.txt_city.setText(data.get(11));
            this.txt_postcode.setText(data.get(12));
            this.txt_state.setText(data.get(13));
            this.txt_country.setText(data.get(14));
            // populate combo box
            List<Person> temp = this.getDeliStaffList();
            Person result = new Person();
            for(Person tempPerson : temp) {
                if(tempPerson.getAccountID().equals(data.get(6))) {
                    result = tempPerson;
                }
            }
            this.txt_assign_to.getSelectionModel().select(result);
            this.cb_auto_assign.setSelected(false);
        } else {
            this.cb_auto_assign.setSelected(true);
        }

        this.toggleAutoAssign();
        
        // set visual representation of data in combo box
        this.txt_assign_to.setConverter(new StringConverter<Person>() {
            @Override
            public String toString(Person person) {
                return person != null ? String.join(" #", person.getName(), person.getAccountID()) : "";
            }

            @Override
            public Person fromString(String string) {
                return null;
            }
        });

        // register combobox listener
        cb_auto_assign.setOnAction(e -> {
            this.toggleAutoAssign();
        });

        btn_submit.setOnMouseClicked(e -> {
            this.processData();
        });
    }

    public void processData() {
        // check form validity
        if(this.isValid()) {
            // unprompt error message
            lbl_err_msg.setVisible(false);
            // add to database
            Person person = new Person(txt_name.getText(), txt_contact.getText());
            Address address = new Address(new String[]{txt_address.getText(), txt_city.getText(), txt_postcode.getText(), txt_state.getText(), txt_country.getText()});
            Order order = new Order();
            order.setOrderName(txt_order_name.getText());
            order.setOrderDesc(txt_order_desc.getText());
            order.setOrderDate(dp_order_date.getValue());
            order.setDeliDate(dp_order_date.getValue());
            order.setIsFragile(cb_is_fragile.isSelected());
            order.setAutoAssign(cb_auto_assign.isSelected());
            order.setAccount(person);
            order.setAddress(address);

            if (this.cb_auto_assign.isSelected()) {
                Random random = new Random();
                order.setAssignTo(this.getDeliStaffList().get(random.nextInt(this.getDeliStaffList().size())));
            } else {
                order.setAssignTo(txt_assign_to.getValue());
            }

            if(title.contains("Create")) {
                FileHandlerOrder fileHandler = new FileHandlerOrder(DatabasePath.Order.getName());
                order.setOrderID(String.valueOf(Integer.parseInt(fileHandler.getLatestID()) + 1));
                order.create();
            } else {
                order.setOrderID(data.get(0));
                order.update();
            }

            // create alert box that prompts for 3 seconds
            Alert a = new Alert(AlertType.INFORMATION); 
            a.setTitle("Success!");
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> a.hide());
            a.show();
            delay.play();

            dashboardController.loadPreviousPage();
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

    public List<Person> getDeliStaffList() {
        FileHandlerUser fileHandlerUser = new FileHandlerUser(DatabasePath.Staff.getName());
        FileHandlerAccount fileHandlerAccount = new FileHandlerAccount(DatabasePath.Account.getName());
        ArrayList<String> tempIDList = new ArrayList<>();
        List<Person> deliStaffList = new ArrayList<Person>();

        List<List<String>> tempUserList = fileHandlerUser.getContent(DatabasePath.Staff.getDataLength());
        for(List<String> tempUser: tempUserList) {
            if(tempUser.get(2).equals(Roles.DeliveryStaff.getRole())) {
                tempIDList.add(tempUser.get(3));
            }
        }

        List<List<String>> tempAccountList = fileHandlerAccount.getContent(DatabasePath.Account.getDataLength());
        for(List<String> tempAccount: tempAccountList) {
            for(String id: tempIDList) {
                if(tempAccount.get(0).equals(id)) {
                    deliStaffList.add(fileHandlerAccount.assignAccount(tempAccount));
                }
            }
        }

        return deliStaffList;
    }

    public void toggleAutoAssign() {
        if (cb_auto_assign.isSelected()) {
            this.txt_assign_to.setDisable(true);
        } else {
            this.txt_assign_to.setDisable(false);
            this.txt_assign_to.getItems().clear();
            this.txt_assign_to.getItems().addAll(this.getDeliStaffList());
        }
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
