package org.aaaa.Enums;

public enum GUIPath {
    AccountUserGateway("/fxml/AccountUserGateway.fxml"),
    AccountEditDialog("/fxml/AccountEditDialog.fxml"),
    AccountAddDialog("/fxml/AccountAddDialog.fxml"),
    AccountOverview("/fxml/AccountOverview.fxml"),
    UserEditDialog("/fxml/UserAddDialog.fxml"),
    UserAddDialog("/fxml/UserEditDialog.fxml"),
    UserOverview("/fxml/UserOverview.fxml"),
    Login("/fxml/Login.fxml"),
    Dashboard("/fxml/Dashboard.fxml"),
    ListViewer("/fxml/ListViewer.fxml"),
    OrderMain("/fxml/OrderMain.fxml"),
    OrderForm("/fxml/OrderForm.fxml"),
    OrderItemHolderShort("/fxml/OrderItemHolderShort.fxml"),
    OrderItemHolderLong("/fxml/OrderItemHolderLong.fxml"),
    Searchbar("/fxml/Searchbar.fxml"),
    RecentLoginItemHolder("/fxml/RecentLoginItemHolder.fxml"),
    ReportMain("/fxml/ReportMain.fxml"),
    ReportItemHolder("/fxml/ReportItemHolder.fxml"),
    FeedbackMain("/fxml/FeedbackMain.fxml"),
    FeedbackForm("/fxml/FeedbackForm.fxml"),
    FeedbackItemHolder("/fxml/FeedbackItemHolder.fxml"),
    DeliveryMain("/fxml/DeliveryMain.fxml"),
    DeliveryForm("/fxml/DeliveryForm.fxml"),
    DeliveryItemHolder("/fxml/DeliveryItemHolder.fxml"),
    DeliveryCancellationItemHolder("/fxml/DeliveryCancellationItemHolder.fxml"),
    PopupBox("/fxml/PopupBox.fxml");


    private final String name;

    GUIPath(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
