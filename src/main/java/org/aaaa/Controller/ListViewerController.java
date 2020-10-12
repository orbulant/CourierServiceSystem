package org.aaaa.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class ListViewerController {
    @FXML
    Label ui_title_label;
    @FXML
    Button ui_title_button;
    @FXML
    ScrollPane content_pane;

    protected String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        ui_title_label.setText(this.title);
    }

    public void setTitleButtonVisibility(boolean value) {
        ui_title_button.setVisible(value);
    }

    public Button getTitleButton() {
        return this.ui_title_button;
    }
}
