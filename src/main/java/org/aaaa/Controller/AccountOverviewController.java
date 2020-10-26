package org.aaaa.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandlerAccount;
import org.aaaa.Person;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
    Label lbl_Role;
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

    //Observable List
    private ObservableList<Person> masterData = FXCollections.observableArrayList();

    public AccountOverviewController(){
        FileHandlerAccount fhAccount = new FileHandlerAccount(DatabasePath.Account.getName());
        List<List<String>> temp = fhAccount.getContent(DatabasePath.Account.getDataLength());
        for(List<String> eachAccount : temp ){
            Person tempPerson = new Person();
            tempPerson.set(eachAccount);
            masterData.add(tempPerson);
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
            // Initialize the person table with the two columns.
            AccountIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccountID()));
            NameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            accountTable.setItems(masterData);
    }


}
