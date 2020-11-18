package org.aaaa.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.GUIPath;
import org.aaaa.FileHandlers.FileHandlerAccount;
import org.aaaa.Person;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

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
    @FXML
    TextField txt_Filter;

    //Observable List
    private ObservableList<Person> masterData = FXCollections.observableArrayList();

    /**
     * Returns the data as an observable list of Persons.
     * @return the masterData
     */
    public ObservableList<Person> getMasterData() {
        return masterData;
    }

    public AccountOverviewController(){
        setMasterData();
    }

    /**
     * Initialize the tableview columns, binding data to the tableview and creating a select listener.
     * @param url default
     * @param resourceBundle default
     */
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

            // 1. Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Person> filteredData = new FilteredList<>(masterData, p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            txt_Filter.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(person -> {
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getAccountID().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches accountID name.
                    } else if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches name.
                    }
                    return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Person> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(accountTable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            accountTable.setItems(sortedData);
    }

    /**STRONG
     * Sets the the tableview (masterData) from the database
     *
     *
     */
    public void setMasterData(){
        masterData.clear();
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
    /**STRONG
     * Updates the account file
     *
     *
     */
    private void PersonUpdateFile(){
        FileHandlerAccount fhAccount = new FileHandlerAccount(DatabasePath.Account.getName());
        try {
            fhAccount.writeAccountFile(accountTable.getItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * Handles deleting persons from the table overview
     */
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
        PersonUpdateFile();
    }


    /**
     * Opens the edit dialog for the specified person. If user clicks on Save, changes are saved into the provided person object and true is returned
     *
     * @param person the person object to be edited
     * @return true if the user clicked on "Save", false otherwise.
     */
    public boolean showAccountEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(GUIPath.AccountEditDialog.getName()));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AccountEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showAccountAddDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(GUIPath.AccountAddDialog.getName()));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AccountAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.addPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isAddClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Called when the user clicks the add button. Opens a dialog to add
     * details for a new person.
     */
    @FXML
    private void handleAddPerson() {
        Person tempPerson = new Person();
        boolean saveClicked = this.showAccountAddDialog(tempPerson);
        if (saveClicked) {
            this.getMasterData().add(tempPerson);
            PersonUpdateFile();
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = accountTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean saveClicked = this.showAccountEditDialog(selectedPerson);
            if (saveClicked) {
                showPersonDetails(selectedPerson);
                PersonUpdateFile();
                setMasterData();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


}
