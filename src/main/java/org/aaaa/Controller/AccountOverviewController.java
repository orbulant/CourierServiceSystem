package org.aaaa.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandler;
import org.aaaa.FileHandlers.FileHandlerAccount;
import org.aaaa.Person;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AccountOverviewController implements Initializable {

    @FXML
    TableView<Person> accountTable;
    @FXML
    TableColumn<Person, String> AccountIDColumn;
    @FXML
    TableColumn<Person, String> NameColumn;
    @FXML
    Label lbl_AccountID;
    @FXML
    Label lbl_Name;
    @FXML
    Label lbl_DOB;
    @FXML
    Label lbl_NRIC;
    @FXML
    Label lbl_ContactNum;
    @FXML
    Label lbl_HouseNum;
    @FXML
    Label lbl_FullAddress;
    @FXML
    Label lbl_City;
    @FXML
    Label lbl_Postcode;
    @FXML
    Label lbl_State;
    @FXML
    Label lbl_Country;
    @FXML
    Label lbl_CreatedBy;
    @FXML
    Label lbl_CreatedOn;
    @FXML
    Label lbl_ChangedBy;
    @FXML
    Label lbl_ChangedOn;
    @FXML
    Button btn_Add;
    @FXML
    Button btn_Edit;
    @FXML
    Button btn_Delete;

    //Observable List
    private final ObservableList<Person> masterData = FXCollections.observableArrayList();

    public AccountOverviewController(){
        FileHandlerAccount fhAccount = new FileHandlerAccount(DatabasePath.Account.getName());
        List<List<String>> temp = fhAccount.getContent(DatabasePath.Account.getDataLength());
        for(List<String> eachAccount : temp ){
            Person tempPerson = new Person();
            tempPerson.set(eachAccount);
            tempPerson.setCreatedBy(eachAccount.get(11));
            tempPerson.setCreatedOn(LocalDateTime.parse(eachAccount.get(12)));
            tempPerson.setChangedBy(eachAccount.get(13));
            tempPerson.setChangedOn(LocalDateTime.parse(eachAccount.get(14)));
            masterData.add(tempPerson);
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
            // Initialize the person table with the two columns
            AccountIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccountID()));
            NameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            //Set items of the accountTable (TableView) with masterData, in this case all the accounts from accounts.txt
            accountTable.setItems(masterData);
            //Clear person details
            showPersonDetails(null);
            //Listener for selection changes which then shows the person details when changed.
            accountTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    private void showPersonDetails(Person person){
        if (person != null){
            //Fill labels on the right hand side with information from the person object.
            lbl_AccountID.setText(person.getAccountID());
            lbl_Name.setText(person.getName());
            lbl_DOB.setText(String.valueOf(person.getDob()));
            lbl_NRIC.setText(person.getNric());
            lbl_ContactNum.setText(person.getContactNum());
            lbl_HouseNum.setText(person.getContactNum());
            lbl_FullAddress.setText(person.getFulladdress().getAddress());
            lbl_City.setText(person.getFulladdress().getCity());
            lbl_Postcode.setText((person.getFulladdress().getPostcode()));
            lbl_State.setText(person.getFulladdress().getState());
            lbl_Country.setText(person.getFulladdress().getCountry());
            lbl_CreatedBy.setText(person.getCreatedBy());
            lbl_CreatedOn.setText(String.valueOf(person.getCreatedOn()));
            lbl_ChangedBy.setText(person.getChangedBy());
            lbl_ChangedOn.setText(String.valueOf(person.getChangedOn()));

        } else {
            //If person is null, remove all text
            lbl_AccountID.setText("");
            lbl_Name.setText("");
            lbl_DOB.setText("");
            lbl_NRIC.setText("");
            lbl_ContactNum.setText("");
            lbl_HouseNum.setText("");
            lbl_FullAddress.setText("");
            lbl_City.setText("");
            lbl_Postcode.setText("");
            lbl_State.setText("");
            lbl_Country.setText("");
            lbl_CreatedBy.setText("");
            lbl_CreatedOn.setText("");
            lbl_ChangedBy.setText("");
            lbl_ChangedOn.setText("");
        }

    }

    @FXML
    private void handleDeletePerson(){
        int selectedIndex = accountTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            accountTable.getItems().remove(selectedIndex);
        } else {
            //Nothing selected then...
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection!");
            alert.setHeaderText("No Person Selected!");
            alert.setContentText("Please select a person in the table!");
            alert.showAndWait();
        }
        DeletePersonUpdateFile();
    }

    private void DeletePersonUpdateFile(){
        FileHandlerAccount fhAccount = new FileHandlerAccount(DatabasePath.Account.getName());
        try {
            fhAccount.deleteAccount(accountTable.getItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
