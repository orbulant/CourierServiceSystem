package org.aaaa.FileHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.CurrentUser;
import org.aaaa.Enums.Status;

public class FileHandlerDelivery extends FileHandler {
    public FileHandlerDelivery(String pathname) {
        super(pathname);
    }
 
    public List<List<String>> getDelivererContent(int datalength, LocalDate date) {
        List<List<String>> originalList = this.getContent(datalength);
        List<List<String>> filteredList = new ArrayList<>();

        for(List<String> original : originalList ) {
            if(date != null && date.compareTo(LocalDate.now()) != 0) {
                if(original.get(6).equals(CurrentUser.getStaff().getAccountID()) && (original.get(7).equals(Status.Canceled.getStatus()) || original.get(7).equals(Status.Delivered.getStatus()))) {
                    if (date.compareTo(LocalDate.parse(original.get(4))) == 0) {
                        filteredList.add(original);
                    }
                }
            } else {
                if(original.get(6).equals(CurrentUser.getStaff().getAccountID()) && original.get(7).equals(Status.Processing.getStatus())) {
                    if(original.get(4).isBlank()) {
                        filteredList.add(original);
                    } else {
                        if(LocalDate.now().compareTo(LocalDate.parse(original.get(4))) >= 0) {
                            filteredList.add(original);
                        }
                    }
                } 
            }
        }

        return filteredList;
    }
}
