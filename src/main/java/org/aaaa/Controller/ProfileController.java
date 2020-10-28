package org.aaaa.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.aaaa.CurrentUser;
import org.aaaa.Person;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    /**
     * FXML Fields
     */
    @FXML
    Label lbl_Name;
    @FXML
    Label lbl_Username;
    @FXML
    Label lbl_Password;
    @FXML
    Label lbl_AccountID;
    @FXML
    Label lbl_NRIC;
    @FXML
    Label lbl_ContactNum;
    @FXML
    Label lbl_HouseNum;
    @FXML
    Label lbl_DOB;
    @FXML
    Label lbl_Role;
    @FXML
    Label lbl_FullAddress;
    @FXML
    Label lbl_City;
    @FXML
    Label lbl_State;
    @FXML
    Label lbl_Postcode;
    @FXML
    Label lbl_Country;

    public void setProfileContent(){
            //Fill labels on the right hand side with information from the person object.
            lbl_AccountID.setText(CurrentUser.getStaff().getAccountID());
            lbl_Name.setText(CurrentUser.getStaff().getPerson().getName());
            lbl_Username.setText(CurrentUser.getStaff().getUsername());
            lbl_Password.setText(CurrentUser.getStaff().getPassword());
            lbl_Role.setText(CurrentUser.getStaff().getRole());
            lbl_DOB.setText(String.valueOf(CurrentUser.getStaff().getPerson().getDob()));
            lbl_NRIC.setText(CurrentUser.getStaff().getPerson().getNric());
            lbl_ContactNum.setText(CurrentUser.getStaff().getPerson().getContactNum());
            lbl_HouseNum.setText(CurrentUser.getStaff().getPerson().getHousenum());
            lbl_FullAddress.setText(CurrentUser.getStaff().getPerson().getFulladdress().getAddress());
            lbl_City.setText(CurrentUser.getStaff().getPerson().getFulladdress().getCity());
            lbl_Postcode.setText(CurrentUser.getStaff().getPerson().getFulladdress().getPostcode());
            lbl_State.setText(CurrentUser.getStaff().getPerson().getFulladdress().getState());
            lbl_Country.setText(CurrentUser.getStaff().getPerson().getFulladdress().getCountry());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProfileContent();
    }
}
