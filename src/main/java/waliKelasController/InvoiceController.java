/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package waliKelasController;

import database.dbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.TableArchive;

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
    private TableColumn<TableArchive, String> col_date;

    @FXML
    private TableColumn<TableArchive, String> col_desription;

    @FXML
    private TableColumn<TableArchive, String> col_id;

    @FXML
    private TableColumn<TableArchive, String> col_income;

    @FXML
    private TableColumn<TableArchive, String> col_outcome;

    @FXML
    private TableView<TableArchive> tableArchive;

    @FXML
    private void print(){
        
    }
    
    @FXML
    public ObservableList<TableArchive> getArchiveList(){
        ObservableList<TableArchive> list = FXCollections.observableArrayList();
        
        Connection conn = dbConnection.connect();
        Statement st;
        ResultSet rs;
        
        String query = "SELECT*FROM archive";
        
        try {
            st = conn.createStatement();
            rs= st.executeQuery(query);
            
            while (rs.next()) {                
                list.add(new TableArchive(rs.getString("archive_id"), rs.getString("income"), rs.getString("outcome"), rs.getString("created_at"), rs.getString("description")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    @FXML
    public void showArchive(){
        ObservableList list = getArchiveList();
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("no"));
        col_income.setCellValueFactory(new PropertyValueFactory<>("income"));
        col_outcome.setCellValueFactory(new PropertyValueFactory<>("outcome"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_desription.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        tableArchive.setItems(list);
        
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
        showArchive();
    }    
    
    
    
}
