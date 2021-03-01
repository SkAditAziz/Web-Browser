package web_browser_final;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.stage.Stage;

public class HistoryController implements Initializable {

    
    @FXML
    private TableView<history> hTable;

    @FXML
    private TableColumn<history, String> URLColumn;

    @FXML
    private TableColumn<history, String> titleColumn;

     @FXML
    private Button refreshButton;

    @FXML
    private Label label;

    @FXML
    private Button openButton;
    
    
     @FXML
    void openPage(ActionEvent event) throws IOException {
           history h = hTable.getSelectionModel().getSelectedItem();
           String url = h.URL;
           
           FXMLDocumentController.address = url;
           
           Stage s = new Stage();
        
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
            Scene scene = new Scene(root);
        
            s.setScene(scene);
            s.show();
          
    }

    @FXML
    void refreshHistory(ActionEvent event) {
        
         WebEngine w = FXMLDocumentController.wEngine;
        history h;
        ObservableList <history> os = FXCollections.observableArrayList();
           
        ObservableList<WebHistory.Entry> his = w.getHistory().getEntries();
        
        for(WebHistory.Entry e : his){
            h = new history(e.getTitle(), e.getUrl());
            os.add(h);
            
            
        }
        
        hTable.setItems(os);    
        
        
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine w = FXMLDocumentController.wEngine;
       
        URLColumn.setCellValueFactory(new PropertyValueFactory<>("URL"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
       
        history h;
        ObservableList <history> os = FXCollections.observableArrayList();
           
        ObservableList<WebHistory.Entry> his = w.getHistory().getEntries();
        
        for(WebHistory.Entry e : his){
            h = new history(e.getTitle(), e.getUrl());
            os.add(h); 
        }
        
        hTable.setItems(os);
        
    }

}
