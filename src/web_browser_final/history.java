/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_browser_final;

/**
 *
 * @author USER
 */
public class history {
       String title;
       String URL;
       
       history(String title, String URL){
           this.title = title;
           this.URL = URL;
       }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
}
