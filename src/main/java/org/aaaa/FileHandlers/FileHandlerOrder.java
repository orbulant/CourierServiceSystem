package org.aaaa.FileHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.CurrentUser;
import org.aaaa.Order;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Status;
import org.aaaa.Enums.Models.OrderModel;

public class FileHandlerOrder extends FileHandler {

    public FileHandlerOrder(String pathname) {
        super(pathname);
    }

    public Order assignOrder(List<String> data) {
        return new Order(data);
    }

    public String getLatestID() {
        List<List<String>> tempList = this.getContent(DatabasePath.Order.getDataLength());
        return tempList.get(tempList.size() - 1).get(OrderModel.OrderID.getIndex());
    }

    public int getTotalDelivered() {
        int counter = 0;
        for(List<String> temp: this.getContent(DatabasePath.Order.getDataLength())) {
            if (temp.get(OrderModel.Status.getIndex()).equals(Status.Delivered.getStatus())) {
                counter ++;
            }
        }

        return counter;
    }

    public int getTotalCancelled() {
        int counter = 0;
        for(List<String> temp: this.getContent(DatabasePath.Order.getDataLength())) {
            if (temp.get(OrderModel.Status.getIndex()).equals(Status.Cancelled.getStatus())) {
                counter ++;
            }
        }

        return counter;
    }

    public List<String> getOrderByID(String id) {
        List<String> result = new ArrayList<>();
        for(List<String> temp: this.getContent(DatabasePath.Order.getDataLength())) {
            if (temp.get(OrderModel.OrderID.getIndex()).equals(id)) {
                result = temp;
            }
        }

        return result;
    }

    public List<List<String>> getDelivererContent(int datalength, LocalDate date) {
        List<List<String>> originalList = this.getContent(datalength);
        List<List<String>> filteredList = new ArrayList<>();

        for(List<String> original : originalList ) {
            if(date != null && date.compareTo(LocalDate.now()) != 0) {
                if(original.get(OrderModel.AssignedTo.getIndex()).equals(CurrentUser.getStaff().getAccountID()) && (original.get(OrderModel.Status.getIndex()).equals(Status.Cancelled.getStatus()) || original.get(OrderModel.Status.getIndex()).equals(Status.Delivered.getStatus()))) {
                    if (date.compareTo(LocalDate.parse(original.get(OrderModel.DeliveryDate.getIndex()))) == 0) {
                        filteredList.add(original);
                    }
                }
            } else {
                if(original.get(OrderModel.AssignedTo.getIndex()).equals(CurrentUser.getStaff().getAccountID()) && original.get(OrderModel.Status.getIndex()).equals(Status.Processing.getStatus())) {
                    if(original.get(OrderModel.DeliveryDate.getIndex()).isBlank()) {
                        filteredList.add(original);
                    } else {
                        if(LocalDate.now().compareTo(LocalDate.parse(original.get(OrderModel.DeliveryDate.getIndex()))) >= 0) {
                            filteredList.add(original);
                        }
                    }
                } 
            }
        }

        return filteredList;
    }
}
