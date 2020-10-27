package org.aaaa.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.aaaa.RecentLogin;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.GUIPath;
import org.aaaa.FileHandlers.FileHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class RecentLoginListViewerController extends ListViewerController implements Initializable {
    @FXML
    ScrollPane content_pane;
    @FXML
    VBox content;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try{
            content.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            FileHandler fileHandler = new FileHandler(DatabasePath.RecentLogin.getName());
            List<List<String>> tempList = fileHandler.getContent(DatabasePath.RecentLogin.getDataLength());
            for(int i = tempList.size(); i > tempList.size() - 10 ; i--) {
                RecentLogin recentLogin = new RecentLogin(tempList.get(i - 1));

                loader = new FXMLLoader(getClass().getResource(GUIPath.RecentLoginItemHolder.getName()));
                // add item controller here
                RecentLoginItemHolderController recentLoginItemHolderController = new RecentLoginItemHolderController(recentLogin);
                loader.setController(recentLoginItemHolderController);

                content.getChildren().add((Node)loader.load());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


