package org.aaaa.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.aaaa.FileHandlers.FileHandlerUser;
import org.aaaa.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserOverviewController implements Initializable {
    @FXML
    TableView<Staff> userTable;
    @FXML
    TableColumn<Staff, String> AccountIDColumn;
    @FXML
    TableColumn<Staff, String> UsernameColumn;
    @FXML
    Label lbl_AccountID;
    @FXML
    Label lbl_Username;
    @FXML
    Label lbl_Password;
    @FXML
    Label lbl_Role;
    @FXML
    Button btn_Add;
    @FXML
    Button btn_Edit;
    @FXML
    Button btn_Delete;

    //Observable List
    private ObservableList<Staff> masterUserData = FXCollections.observableArrayList();

    /**
     * Returns the data as an observable list of Persons.
     * @return the masterUserData
     */
    public ObservableList<Staff> getmasterUserData() {
        return masterUserData;
    }

    public UserOverviewController(){
        setmasterUserData();
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
        UsernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        //Set items of the userTable (TableView) with masterUserData, in this case all the accounts from accounts.txt
        userTable.setItems(masterUserData);
        //Clear person details
        showStaffDetails(null);
        //Listener for selection changes which then shows the person details when changed.
        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showStaffDetails(newValue));
    }

    /**STRONG
     * Sets the the tableview (masterUserData) from the database
     *
     *
     */
    public void setmasterUserData(){
        masterUserData.clear();
        FileHandlerUser fhUser = new FileHandlerUser(DatabasePath.Staff.getName());
        List<List<String>> temp = fhUser.getContent(DatabasePath.Staff.getDataLength());
        for(List<String> eachAccount : temp ){
            Staff tempStaff = new Staff();
            tempStaff.set(eachAccount);
            masterUserData.add(tempStaff);
        }
    }
    /**STRONG
     * Updates the account file
     *
     *
     */
    private void StaffUpdateFile(){
        FileHandlerUser fhUser = new FileHandlerUser(DatabasePath.Staff.getName());
        try {
            fhUser.writeStaffFile(userTable.getItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showStaffDetails(Staff staff){
        if (staff != null){
            //Fill labels on the right hand side with information from the person object.
            lbl_AccountID.setText(staff.getAccountID());
            lbl_Username.setText(staff.getUsername());
            lbl_Password.setText(staff.getPassword());
            lbl_Role.setText(staff.getRole());
        } else {
            //If person is null, remove all text
            lbl_AccountID.setText("");
            lbl_Username.setText("");
            lbl_Password.setText("");
            lbl_Role.setText("");
            
        }
    }

    /**
     * Handles deleting persons from the table overview
     */
    @FXML
    private void handleDeleteStaff(){
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            userTable.getItems().remove(selectedIndex);
        } else {
            //Nothing selected then...
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection!");
            alert.setHeaderText("No Person Selected!");
            alert.setContentText("Please select a person in the table!");
            alert.showAndWait();
        }
        StaffUpdateFile();
    }

    @FXML
    private void handleAddStaff() {
        Staff tempStaff = new Staff();
        boolean saveClicked = this.showUserAddDialog(tempStaff);
        if (saveClicked) {
            this.getmasterUserData().add(tempStaff);
            StaffUpdateFile();
        }
    }

    @FXML
    private void handleEditStaff() {
        Staff selectedStaff = userTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            boolean saveClicked = this.showUserEditDialog(selectedStaff);
            if (saveClicked) {
                showStaffDetails(selectedStaff);
                StaffUpdateFile();
                setmasterUserData();
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

    public boolean showUserEditDialog(Staff staff) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(GUIPath.UserEditDialog.getName()));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Staff");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            UserEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStaff(staff);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showUserAddDialog(Staff staff) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(GUIPath.UserAddDialog.getName()));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Staff");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            UserAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.addStaff(staff);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isAddClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
