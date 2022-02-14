package waliKelasController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import database.dbConnection;
import waliKelasModel.StudentModel;

/**
 * FXML Controller class
 *
 * @author zahro
 */
public class StudentController implements Initializable {

    @FXML
    private TableView<StudentModel> StudentTable;
    @FXML
    private TableColumn<StudentModel, String> col_id;
    @FXML
    private TableColumn<StudentModel, String> col_name;
    @FXML
    private TableColumn<StudentModel, String> col_number;
    @FXML
    private TableColumn<StudentModel, String> col_total_paid;
    @FXML
    private TextField txt_name;
 
    
    ObservableList<StudentModel> list = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        

        try {
            Connection conn = dbConnection.connect();
            ResultSet rs = conn.createStatement().executeQuery("SELECT*FROM student");
            
            while (rs.next()) {                
                list.add(new StudentModel(rs.getString("student_id"), rs.getString("name"), rs.getString("number"), rs.getString("total_paid")));
            }
            
        } catch (SQLException e) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null,e);
        }
        
        
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_total_paid.setCellValueFactory(new PropertyValueFactory<>("total_paid"));
        
        StudentTable.setItems(list);
    }    
    
}
