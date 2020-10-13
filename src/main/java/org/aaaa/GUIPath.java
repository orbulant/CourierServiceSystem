package org.aaaa;

public enum GUIPath {
    Dashboard("/fxml/Dashboard.fxml"),
    ListViewer("/fxml/ListViewer.fxml"),
    OrderMain("/fxml/OrderMain.fxml"),
    OrderForm("/fxml/OrderForm.fxml"),
    Searchbar("/fxml/Searchbar.fxml"),
    ReportMain("/fxml/ReportMain.fxml");

    private final String name;

    GUIPath(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
