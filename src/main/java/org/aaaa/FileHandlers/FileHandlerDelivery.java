package org.aaaa.FileHandlers;

import java.util.ArrayList;
import java.util.List;

import org.aaaa.DeliveryCancellation;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Status;

public class FileHandlerDelivery extends FileHandler {
    public FileHandlerDelivery(String pathname) {
        super(pathname);
    }

    public DeliveryCancellation assignDeliveryCancellation(List<String> data) {
        return new DeliveryCancellation(data);
    }

    public List<List<String>> getUnapprovedCancellations() {
        List<List<String>> originalList = this.getContent(DatabasePath.DeliveryCancellation.getDataLength());
        List<List<String>> filteredList = new ArrayList<>();

        for(List<String> temp : originalList) {
            if(temp.get(3).equals(Status.Cancelling.getStatus())) {
                filteredList.add(temp);
            }
        }

        return filteredList;
    }
}
