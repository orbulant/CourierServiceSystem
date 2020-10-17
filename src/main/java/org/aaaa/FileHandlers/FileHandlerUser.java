package org.aaaa.FileHandlers;

import org.aaaa.Staff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerUser extends FileHandler{
    //CONSTRUCTOR
    public FileHandlerUser(String pathname) { super(pathname); }

    //BODY
    ////////////////////////////////OVERRIDES SEARCH
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
    ////////////////////////////////OVERLOADS UPDATE
    public void update(Staff staff)  throws IOException{
        List<List<String>> userfile = getContent(8);
        //Create a file object
        File file = new File(pathname);
        //Creates a fileWriter object
        FileWriter writer = new FileWriter(file);

        for (List<String> user : userfile) {
            if (user.get(0).equals(staff.getAccountID())) {
                user.set(0, staff.getAccountID());
                user.set(1, staff.getUsername());
                user.set(2, staff.getPassword());
                user.set(3, staff.getRole());
                user.set(4, staff.getCreatedBy());
                user.set(5, staff.getCreatedOn().toString());
                user.set(6, staff.getChangedBy());
                user.set(7, staff.getChangedOn().toString());
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
