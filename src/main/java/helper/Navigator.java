/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author zahro
 */
public class Navigator{
     Stage stage;
    public void switchTo(String url) throws IOException {
       
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(url+".fxml"));

        stage.setScene(loader.load());
        stage.show();  
    };
    
}
