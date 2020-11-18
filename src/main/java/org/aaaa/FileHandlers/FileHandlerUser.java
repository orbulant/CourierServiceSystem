package org.aaaa.FileHandlers;

import org.aaaa.Enums.Roles;
import org.aaaa.Staff;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Models.UserModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerUser extends FileHandler{
    //CONSTRUCTOR
    public FileHandlerUser(String pathname) {
        super(pathname);
    }

    public Staff getUserByAccountID(String accountID) {
        Staff staff = new Staff();
        List<List<String>> tempList = this.getContent(DatabasePath.Staff.getDataLength());
        for(List<String> temp: tempList) {
            if(temp.get(UserModel.AccountID.getIndex()).equals(accountID)) {
                staff = assignUser(temp);
            }
        }
        return staff;
    }

    public int getTotalUsers() {
        return this.getContent(DatabasePath.Staff.getDataLength()).size();
    }

    public Staff assignUser(List<String> user) {
        return new Staff(user);
    }

    //BODY
    ////////////////////////////////SEARCH
    @Override
    public List<String> search(String searchable){
        int L = 8;
        List<String> result = new ArrayList<>();
        try{
            for (int i = 0; i < this.getContent(L).size(); i++) {
                if (this.getContent(L).get(i).get(0).equals(searchable)) {
                    for(int x = 0; x < 8; x++) {
                        result.add(getContent(L).get(i).get(x));
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /*
     Write user.txt with new stuff
      */
    public void writeStaffFile(List<Staff> buffer) throws IOException {
        //Create a file object
        File file = new File(pathname);
        //Creates a fileWriter object
        FileWriter writer = new FileWriter(file);
        //Write content to file
        //Writing array of objects separated by comma
        for(Staff innerstuff : buffer){
            writer.write(innerstuff.getUsername() + "\n");
            writer.write(innerstuff.getPassword() + "\n");
            writer.write(innerstuff.getRole() + "\n");
            writer.write(innerstuff.getPerson().getAccountID() + "\n");
        }
        writer.flush();
        writer.close();
    }
    /*
   Update Staff
    */
    public void update(Staff staff) throws IOException{
        //Create a file object
        File file = new File(pathname);
        //Creates a fileWriter object
        FileWriter writer = new FileWriter(file);
        //Creates a multi-dimensional list named buffer.
        List<List<String>> buffer = getContent(DatabasePath.Staff.getDataLength());

        for (List<String> item : buffer) {
            if (staff.getAccountID().equals(item.get(0))){
                item.set(0, staff.getAccountID());
                item.set(1, staff.getUsername());
            }
        }

        for(List<String> innerstuff : buffer){
            for(String s : innerstuff){
                writer.write(s + "\n");
            }
        }
        //Writing array of objects separated by comma and new line
        writer.flush();
        writer.close();
    }

    public int getTotalDeliveryman(){
        int counter = 0;
        for(List<String> temp: this.getContent(DatabasePath.Staff.getDataLength())) {
            if(temp.get(UserModel.Role.getIndex()).equals(Roles.DeliveryStaff.getRole())) {
                counter ++;
            }
        }

        return counter;
    }
}