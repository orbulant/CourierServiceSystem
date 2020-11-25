package org.aaaa.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandlerUser;
import org.aaaa.Person;
import org.aaaa.Staff;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserAddDialogController implements Initializable {

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
    ChoiceBox<String> choiceBox_Role = new ChoiceBox<>();
    /*
    Fields
     */
    private Staff staff;
    private boolean addClicked = false;
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
     * Sets the staff to be edited in the edit dialog.
     *
     * @param staff to be added in the dialog stage
     */
    public void addStaff(Staff staff){
        FileHandlerUser fhUs = new FileHandlerUser(DatabasePath.Staff.getName());
        List<List<String>> buffer = fhUs.getContent(DatabasePath.Staff.getDataLength());
        List<String> strings = buffer.get(buffer.size() - 1);
        String latestuserID = strings.get(strings.size() - 1);
        int i = Integer.parseInt(latestuserID);
        int toadd = i + 1;
        this.staff = staff;
        txt_AccountID.setText(String.valueOf(toadd));
        txt_Username.setText("");
        txt_Password.setText("");
        choiceBox_Role.setValue("delivery");
    }

    /**
     * Returns true if the user clicked save, false if not clicked.
     *
     * @return true if save button is clicked
     */
    public boolean isAddClicked(){ return addClicked; }

    @FXML
    private void handleAdd(){
        if(isInputValid()){
            Person newPerson = new Person();
            newPerson.setAccountID(txt_AccountID.getText());
            staff.setPerson(newPerson);
            staff.setUsername(txt_Username.getText());
            staff.setPassword(txt_Password.getText());
            staff.setRole(choiceBox_Role.getValue());

            addClicked = true;
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

        if (txt_Username.getText() == null || txt_Username.getText().length() == 0) {
            errorMessage += "No valid Username!\n";
        }
        if (txt_Password.getText() == null || txt_Password.getText().length() == 0) {
            errorMessage += "No valid Password!\n";
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
