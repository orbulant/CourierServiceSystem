package org.aaaa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Models.FeedbackModel;
import org.aaaa.FileHandlers.FileHandler;
import org.aaaa.FileHandlers.FileHandlerOrder;

public class Feedback extends Data {
    private String feedbackID;
    private Order order;
    private String feedback;
    private LocalDate feedbackDate;
    private FileHandler fileHandler = new FileHandler(DatabasePath.Feedback.getName());
    // private FileHandlerFeedback fileHandlerFeedback;

    public Feedback() {
        this.feedbackID   = "";
        this.feedback     = "";
        this.order        = new Order();
        this.feedbackDate = LocalDate.now();
    }

    public Feedback(List<String> data) {
        this.set(data);
    }

    @Override
    public List<String> get() {
        List<String> result = new ArrayList<>();

        result.add(this.feedbackID);
        result.add(order.getOrderID());
        result.add(this.feedback);
        result.add(this.feedbackDate.toString());
        result.addAll(this.getCreatedInfo());
        result.addAll(this.getChangedInfo());

        return result;
    }

    @Override
    public void set(List<String> data) {
        FileHandlerOrder fileHandlerOrder = new FileHandlerOrder(DatabasePath.Order.getName());
        
        this.feedbackID   = data.get(FeedbackModel.FeedbackID.getIndex());
        this.feedback     = data.get(FeedbackModel.Feedback.getIndex());
        this.feedbackDate = LocalDate.parse(data.get(FeedbackModel.FeedbackDate.getIndex()));
        this.order        = fileHandlerOrder.assignOrder(fileHandlerOrder.getOrderByID(data.get(FeedbackModel.OrderID.getIndex())));
    }

    @Override
    public void create() {
        this.setCreatedInfo();
        //create arraylist and write to file
        try{
            fileHandler.addContent(fileHandler.getContent(DatabasePath.Feedback.getDataLength()), this.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        this.setChangedInfo();
        //update arraylist and replace in file
        try{
            fileHandler.update(fileHandler.getContent(DatabasePath.Feedback.getDataLength()), this.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
