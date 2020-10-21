package org.aaaa.Popup;

import org.aaaa.Controller.PopupBoxController;
import org.aaaa.Enums.GUIPath;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PopupBox {
    private String title;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private PopupBoxController popupBoxController;
    private StringProperty textAreaProperty;

    public PopupBox(String title, String message) {
        this.title = title;
        this.textAreaProperty = new SimpleStringProperty();
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(GUIPath.PopupBox.getName()));
            popupBoxController = new PopupBoxController(message);
            loader.setController(popupBoxController);

            popupBoxController.getCancelProperty().addListener((v, oldValue, newValue) -> {
                if (newValue) {
                    stage.close();
                }
            });

            popupBoxController.getConfirmProperty().addListener((v, oldValue, newValue) -> {
                if (newValue) {
                    this.textAreaProperty.set(popupBoxController.getTextArea().getText());
                    stage.close();
                }
            });

            root = loader.load();
            scene = new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        stage = new Stage();
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        stage.setTitle(this.title);
        stage.setScene(scene);
        stage.showAndWait();
    }

    
    public PopupBoxController getPopupBoxController() {
        return popupBoxController;
    }

    public void setPopupBoxController(PopupBoxController popupBoxController) {
        this.popupBoxController = popupBoxController;
    }

    public StringProperty getTextAreaProperty() {
        return textAreaProperty;
    }

    public void setTextAreaProperty(StringProperty textAreaProperty) {
        this.textAreaProperty = textAreaProperty;
    }
}
