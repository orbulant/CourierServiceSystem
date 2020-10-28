package org.aaaa.Controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;

import org.aaaa.DeliveryCancellation;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Status;
import org.aaaa.FileHandlers.FileHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeliveryCancellationItemHolderController implements Initializable {
    @FXML
    private Label lbl_one;
    @FXML
    private Label lbl_two;
    @FXML
    private Label lbl_three;
    @FXML
    private Label lbl_four;
    @FXML
    private Button btn_one;
    @FXML
    private Button btn_two;

    private FileHandler fileHandler;
    private DeliveryCancellation deliveryCancellation;
    private DeliveryCancellationListViewerController deliveryCancellationListViewerController;

    public DeliveryCancellationItemHolderController(){}

    public DeliveryCancellationItemHolderController(DeliveryCancellation deliveryCancellation) {
        fileHandler = new FileHandler(DatabasePath.DeliveryCancellation.getName());
        this.deliveryCancellation = deliveryCancellation;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (deliveryCancellation != null) {
            lbl_one.setText(String.join("/", deliveryCancellation.getOrder().getOrderID(), deliveryCancellation.getOrder().getOrderName()));
            lbl_two.setText(String.join("/", deliveryCancellation.getAccountID(), deliveryCancellation.getStaff().getPerson().getName()));
            lbl_three.setText(deliveryCancellation.getReason());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            lbl_four.setText(deliveryCancellation.getRequestDate().format(formatter));

            btn_one.setOnMouseClicked(e -> {
                try{
                    // change order status
                    deliveryCancellation.getOrder().setStatus(Status.Cancelled.getStatus());
                    deliveryCancellation.getOrder().update();

                    deliveryCancellation.setStatus(Status.Approved.getStatus());
                    fileHandler.update(fileHandler.getContent(DatabasePath.DeliveryCancellation.getDataLength()), deliveryCancellation.get());
                    this.deliveryCancellationListViewerController.getDashboardController().loadManagingContent();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });

            btn_two.setOnMouseClicked(e -> {
                try{
                    // assign to new deliverer
                    Random random = new Random();
                    OrderFormController orderFormController = new OrderFormController();
                    deliveryCancellation.getOrder().setAssignTo(orderFormController.getDeliStaffList().get(random.nextInt(orderFormController.getDeliStaffList().size())));
                    deliveryCancellation.getOrder().setStatus(Status.Processing.getStatus());
                    deliveryCancellation.getOrder().update();

                    deliveryCancellation.setStatus(Status.Rejected.getStatus());
                    fileHandler.update(fileHandler.getContent(DatabasePath.DeliveryCancellation.getDataLength()), deliveryCancellation.get());
                    this.deliveryCancellationListViewerController.getDashboardController().loadManagingContent();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            });
        }
    }

    public DeliveryCancellationListViewerController getDeliveryCancellationListViewerController() {
        return deliveryCancellationListViewerController;
    }

    public void setDeliveryCancellationListViewerController(DeliveryCancellationListViewerController deliveryCancellationListViewerController) {
        this.deliveryCancellationListViewerController = deliveryCancellationListViewerController;
    }

    public Label getLblOne() {
        return lbl_one;
    }

    public void setLblOne(Label lbl_one) {
        this.lbl_one = lbl_one;
    }

    public Label getLblTwo() {
        return lbl_two;
    }

    public void setLblTwo(Label lbl_two) {
        this.lbl_two = lbl_two;
    }

    public Label getLblThree() {
        return lbl_three;
    }

    public void setLblThree(Label lbl_three) {
        this.lbl_three = lbl_three;
    }

    public Label getLblFour() {
        return lbl_four;
    }

    public void setLblFour(Label lbl_four) {
        this.lbl_four = lbl_four;
    }

    public Button getBtnOne() {
        return btn_one;
    }

    public void setBtnOne(Button btn_one) {
        this.btn_one = btn_one;
    }

    public Button getBtnTwo() {
        return btn_two;
    }

    public void setBtnTwo(Button btn_two) {
        this.btn_two = btn_two;
    }
}
