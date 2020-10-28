package org.aaaa.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.aaaa.Staff;

import java.net.URL;
import java.util.ResourceBundle;

public class UserEditDialogController implements Initializable {

    /*
    FXML Fields
     */
    @FXML
    TextField txt_AccountID;
    @FXML
    TextField txt_Username;
    @FXML
    TextField txt_Password;
    @FXML
    ChoiceBox<String> choiceBox_Role;

    /*
    Fields
     */
    private Staff staff;
    private boolean saveClicked = false;
    private Stage dialogStage;

    /**
     *
     * Initializes the controller class.
     * Initialize calls from the fxml file.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox_Role.getItems().addAll("delivery", "managing");
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
     * @param staff to be edited in the dialog stage
     */
    public void setStaff(Staff staff){
        this.staff = staff;
        txt_AccountID.setText(staff.getAccountID());
        txt_Username.setText(staff.getUsername());
        txt_Password.setText(staff.getPassword());
        choiceBox_Role.setValue("delivery");
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
            staff.setAccountID(txt_AccountID.getText());
            staff.setUsername(txt_Username.getText());
            staff.setPassword(txt_Password.getText());
            staff.setRole(choiceBox_Role.getValue());

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
        if (txt_Username.getText() == null || txt_Username.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }
        if (txt_Password.getText() == null || txt_Password.getText().length() == 0) {
            errorMessage += "No valid password!\n";
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
