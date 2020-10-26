package org.aaaa.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.aaaa.Address;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.FileHandlers.FileHandlerAccount;
import org.aaaa.FileHandlers.FileHandlerUser;
import org.aaaa.Person;
import org.aaaa.Staff;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class AccountController implements Initializable {




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializing FileHandler objects.
        FileHandlerAccount fhAccount = new FileHandlerAccount(DatabasePath.Account.getName());
        FileHandlerUser fhUser = new FileHandlerUser(DatabasePath.User.getName());

        //Get contents from file.
        List<List<String>> allUser = fhUser.getContent(4);
        List<List<String>> allAccounts = fhAccount.getContent(15);

        //Observable Lists of Staff Object.
        ObservableList<Staff> masterUserData = FXCollections.observableArrayList();
        ObservableList<Person> masterAccountData = FXCollections.observableArrayList();

        for (List<String> eachUser : allUser) {
            //Adding each staff object to the observable list.
            Staff eachStaff = fhUser.assignUser(eachUser);
            masterUserData.add(eachStaff);
        }
        
        for (List<String> eachAcc : allAccounts){
            Person eachPerson = fhAccount.assignAccount(eachAcc);
            //Adding each staff object to the observable list.
            masterAccountData.add(eachPerson);
        }
    }
}
