/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_browser_final;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class FXMLDocumentController implements Initializable {
    
    public static String address = null;
    
    public static WebEngine wEngine;
    
    @FXML
    private Button historyButton;

    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private HBox hbox;

    @FXML
    private WebView webView;

    @FXML
    private TextField googleSearch;

    @FXML
    private Tab tab1;

    @FXML
    private Button goButton;

    @FXML
    private TextField txtURL;

    @FXML
    private TabPane tabPane;

    @FXML
    private Button backButton;

    @FXML
    private Button forwardButton;
    
    @FXML
    private Button newTabButton;
    
    @FXML
    private Tab newTab;

    private WebEngine webEngine;
    

    @FXML
    private void goAction(ActionEvent evt) {
        webEngine.load(txtURL.getText().startsWith("https://") ? txtURL.getText() : "https://" + txtURL.getText());
    }

    public String url = "https://www.google.com";
    public StringTokenizer surl;

    public void gohere(String url) {
        this.url = url;
        webEngine.load(url);
    }

    @FXML
    private void viewhistory(ActionEvent event) throws IOException {

        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("history.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();  
       
        //System.out.println(his);
        
    }
    


    @FXML
    void GoGoogle(ActionEvent event) throws Exception {
        surl = new StringTokenizer(googleSearch.getText());
        StringBuffer surl1 = new StringBuffer("https://www.google.com/search?q=");
        int f = 0;
        while (surl.hasMoreTokens()) {
            if (f != 0) {
                surl1.append('+');
            }
            surl1.append(surl.nextToken());
            f++;
        }
        surl1.append("&ie=utf-8&oe=utf-8");
        url = String.valueOf(surl1);
        if (!(url.isEmpty())) {
            gohere(url);
        }
    }

    @FXML
    public void backFunction(javafx.event.ActionEvent event) {

        int sizeHistory = webEngine.getHistory().getEntries().size();
        if (sizeHistory > 1) {

            WebHistory.Entry entry = webEngine.getHistory().getEntries().get(sizeHistory - 2);

            webEngine.load(entry.getUrl());
        }
    }

    @FXML
    public void forwardFunction(javafx.event.ActionEvent event) {

        int sizeHistory = webEngine.getHistory().getEntries().size();
        if (sizeHistory > 1) {

            WebHistory.Entry entry = webEngine.getHistory().getEntries().get(sizeHistory);
            //System.out.println("URL hist.: " + entry.getUrl());
            webEngine.load(entry.getUrl());
        }
    }
    

    @FXML
    public void openTab(ActionEvent event) throws IOException{
        Stage s = new Stage();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        s.setScene(scene);
        s.show();
        
        
    }
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        webEngine = webView.getEngine();
        
            wEngine = webEngine;

        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txtURL.setText(newValue);
                
            }
            
            
        });
        
        if(address!=null){
            
             webEngine.load(address);
               address = null;
         }
        
        
        
    }
}
