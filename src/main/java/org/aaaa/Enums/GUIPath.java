package org.aaaa.Enums;

public enum GUIPath {
    Login("/fxml/Login.fxml"),
    Dashboard("/fxml/Dashboard.fxml"),
    ListViewer("/fxml/ListViewer.fxml"),
    OrderMain("/fxml/OrderMain.fxml"),
    OrderForm("/fxml/OrderForm.fxml"),
    OrderItemHolderShort("/fxml/OrderItemHolderShort.fxml"),
    OrderItemHolderLong("/fxml/OrderItemHolderLong.fxml"),
    Searchbar("/fxml/Searchbar.fxml"),
    ReportMain("/fxml/ReportMain.fxml"),
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
