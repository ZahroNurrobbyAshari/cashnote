/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package waliKelasController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zahro
 */
public class InvoiceController implements Initializable {
    
     @FXML
    private Button btn_finance;

    @FXML
    private Button btn_print;

    @FXML
    private Button btn_students;

    @FXML
    private ChoiceBox<?> choice_date;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_desription;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_income;

    @FXML
    private TableColumn<?, ?> col_outcome;

    @FXML
    private TableView<?> tableArchive;

    @FXML
    private void print(){
        
    }
    
     public void switchToFinance() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/waliKelas/finance.fxml"));
        Stage window = (Stage)btn_finance.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    
    public void switchToStudent() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/waliKelas/student.fxml"));
        Stage window = (Stage)btn_students.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
