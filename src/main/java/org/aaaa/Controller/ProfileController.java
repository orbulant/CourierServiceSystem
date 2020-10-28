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
    @FXML
    Label lbl_CreatedBy;
    @FXML
    Label lbl_CreatedOn;
    @FXML
    Label lbl_ChangedBy;
    @FXML
    Label lbl_ChangedOn;

    public void setProfileContent(){
            //Fill labels on the right hand side with information from the person object.
            lbl_AccountID.setText(CurrentUser.getStaff().getAccountID());
            lbl_Name.setText(CurrentUser.getStaff().getPerson().getName());
            lbl_DOB.setText(String.valueOf(CurrentUser.getStaff().getPerson().getDob()));
            lbl_NRIC.setText(CurrentUser.getStaff().getPerson().getNric());
            lbl_ContactNum.setText(CurrentUser.getStaff().getPerson().getContactNum());
            lbl_HouseNum.setText(CurrentUser.getStaff().getPerson().getHousenum());
            lbl_FullAddress.setText(CurrentUser.getStaff().getPerson().getFulladdress().getAddress());
            lbl_City.setText(CurrentUser.getStaff().getPerson().getFulladdress().getCity());
            lbl_Postcode.setText(CurrentUser.getStaff().getPerson().getFulladdress().getPostcode());
            lbl_State.setText(CurrentUser.getStaff().getPerson().getFulladdress().getState());
            lbl_Country.setText(CurrentUser.getStaff().getPerson().getFulladdress().getCountry());
            lbl_CreatedBy.setText(CurrentUser.getStaff().getPerson().getCreatedBy());
            lbl_CreatedOn.setText(String.valueOf(CurrentUser.getStaff().getPerson().getCreatedOn()));
            lbl_ChangedBy.setText(CurrentUser.getStaff().getPerson().getChangedBy());
            lbl_ChangedOn.setText(String.valueOf(CurrentUser.getStaff().getPerson().getChangedOn()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProfileContent();
    }
}
