package org.aaaa.FileHandlers;

import org.aaaa.CurrentUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerUser extends FileHandler{
    //CONSTRUCTOR
    public FileHandlerUser(String pathname) { super(pathname); }

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

    public void writeContent(List<List<String>> userfile ,CurrentUser currentUser) throws IOException{
        //Create a file object
        File file = new File(pathname);
        //Creates a fileWriter object
        FileWriter writer = new FileWriter(file);

        for (List<String> user : userfile) {
            if (user.get(0).equals(CurrentUser.getStaff().getAccountID())) {
                user.set(0, CurrentUser.getStaff().getAccountID());
                user.set(1, CurrentUser.getStaff().getUsername());
                user.set(2, CurrentUser.getStaff().getPassword());
                user.set(3, CurrentUser.getStaff().getRole());
                user.set(4, CurrentUser.getStaff().getCreatedBy());
                user.set(5, CurrentUser.getStaff().getCreatedOn().toString());
                user.set(6, CurrentUser.getStaff().getChangedBy());
                user.set(7, CurrentUser.getStaff().getChangedOn().toString());
            }
        }

        for(List<String> innerstuff : userfile){
            for(String s : innerstuff){
                writer.write(s + "\n");
            }
        }
        //Writing array of objects separated by comma and new line
        writer.flush();
        writer.close();
    }


}
