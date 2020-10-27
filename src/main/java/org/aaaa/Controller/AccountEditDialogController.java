package org.aaaa.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.aaaa.Address;
import org.aaaa.CurrentUser;

import org.aaaa.Person;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AccountEditDialogController implements Initializable {

    /*
    FXML Fields
     */
    @FXML
    TextField txt_AccountID;
    @FXML
    TextField txt_Name;
    @FXML
    TextField txt_Nric;
    @FXML
    TextField txt_DOB;
    @FXML
    TextField txt_ContactNum;
    @FXML
    TextField txt_HouseNum;
    @FXML
    TextField txt_FullAddress;
    @FXML
    TextField txt_City;
    @FXML
    TextField txt_State;
    @FXML
    TextField txt_Postcode;
    @FXML
    TextField txt_Country;
    @FXML
    Label lbl_CreatedBy;
    @FXML
    Label lbl_CreatedOn;
    @FXML
    Label lbl_ChangedBy;
    @FXML
    Label lbl_ChangedOn;

    /*
    Fields
     */
    private Person person;
    private boolean saveClicked = false;
    private Stage dialogStage;

    /**
     *
     * Initializes the controller class.
     * Initialize calls from the fxml file.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Sets the dialog stage.
     *
     * @param dialogStage will be set
     */
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the edit dialog.
     *
     * @param person to be edited in the dialog stage
     */
    public void setPerson(Person person){
        this.person = person;
            txt_AccountID.setText(person.getAccountID());
            txt_Name.setText(person.getName());
            txt_DOB.setText(String.valueOf(person.getDob()));
            txt_Nric.setText(person.getNric());
            txt_ContactNum.setText(person.getContactNum());
            txt_HouseNum.setText(person.getContactNum());
            txt_FullAddress.setText(person.getFulladdress().getAddress());
            txt_City.setText(person.getFulladdress().getCity());
            txt_Postcode.setText((person.getFulladdress().getPostcode()));
            txt_State.setText(person.getFulladdress().getState());
            txt_Country.setText(person.getFulladdress().getCountry());
            lbl_CreatedBy.setText(person.getCreatedBy());
            lbl_CreatedOn.setText(String.valueOf(person.getCreatedOn()));
            lbl_ChangedBy.setText(person.getChangedBy());
            lbl_ChangedOn.setText(String.valueOf(person.getChangedOn()));

    }

    /**
     * Returns true if the user clicked save, false if not clicked.
     *
     * @return true if save button is clicked
     */
    public boolean isSaveClicked(){
        return saveClicked;
    }

    @FXML
    private void handleSave(){
        if(isInputValid()){
            person.setAccountID(txt_AccountID.getText());
            person.setName(txt_Name.getText());
            person.setDob(LocalDate.parse(txt_DOB.getText()));
            person.setNric(txt_Nric.getText());
            person.setContactNum(txt_ContactNum.getText());
            person.setHousenum(txt_HouseNum.getText());
            String[] Address = new String[5];
            Address[0] = txt_FullAddress.getText();
            Address[1] = txt_City.getText();
            Address[2] = txt_Postcode.getText();
            Address[3] = txt_State.getText();
            Address[4] = txt_Country.getText();
            Address addressbuffer = new Address(Address);
            person.setFulladdress(addressbuffer);
            person.setChangedBy(CurrentUser.getStaff().getUsername());
            person.setChangedOn(LocalDateTime.now());

            saveClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the cancel button is clicked.
     */
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    /**
     * Some validation
     * @return true if input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (txt_AccountID.getText() == null || txt_AccountID.getText().length() == 0) {
            errorMessage += "No valid account ID!\n";
        }
        if (txt_Name.getText() == null || txt_Name.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (txt_Nric.getText() == null || txt_Nric.getText().length() == 0) {
            errorMessage += "No valid NRIC!\n";
        }

        if (txt_ContactNum.getText() == null || txt_ContactNum.getText().length() == 0) {
            errorMessage += "No valid contact number!\n";
        }

        if (txt_FullAddress.getText() == null || txt_FullAddress.getText().length() == 0) {
            errorMessage += "No valid address!\n";
        }

        if (txt_DOB.getText() == null || txt_DOB.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
