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
import java.io.IOException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Students;

/**
 * FXML Controller class
 *
 * @author zahro
 */
public class StudentController implements Initializable {

    @FXML
    private TableView<Students> StudentTable;
    @FXML
    private TableColumn<Students, String> col_id;
    @FXML
    private TableColumn<Students, String> col_name;
    @FXML
    private TableColumn<Students, String> col_number;
    @FXML
    private TableColumn<Students, String> col_total_paid;
    @FXML
    private TextField txt_name;
    @FXML 
    private TextField txt_number;
    @FXML
    private TextField txt_id;
    @FXML 
    private Button btn_add;
    @FXML 
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML 
    private Button btn_reset;
    @FXML
    private Button btn_finance;
    @FXML
    private Button btn_invoice;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        if (event.getSource()==btn_add) {
            insertStudent();
        }
        else if(event.getSource()==btn_update){
            updateStudent();
        }
        else if(event.getSource()==btn_delete){
            deleteStudent();
        }
        else if(event.getSource()==btn_reset){
            resetForm();
        }
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event){
        Students student =   StudentTable.getSelectionModel().getSelectedItem();
        txt_name.setText(student.getName());
        txt_number.setText(student.getNumber());
        txt_id.setText(student.getStudent_id());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showStudents();

    }

    public ObservableList<Students> getStudentsList() {
        ObservableList<Students> studentList = FXCollections.observableArrayList();
        Connection conn = dbConnection.connect();
        String query = "SELECT * FROM student";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {
               studentList.add(new Students(rs.getString("student_id"), rs.getString("name"), rs.getString("number"), rs.getString("total_paid")));
            }

        } catch (Exception e) {
        }
        return studentList;

    }

    public void showStudents() {
        ObservableList<Students> list = getStudentsList();

        col_id.setCellValueFactory(new PropertyValueFactory<Students, String>("student_id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Students, String>("name"));
        col_number.setCellValueFactory(new PropertyValueFactory<Students, String>("number"));
        col_total_paid.setCellValueFactory(new PropertyValueFactory<Students, String>("total_paid"));

        StudentTable.setItems(list);
    
    }

    public void insertStudent() {
        String query = "INSERT INTO student(name,number) VALUES('"+txt_name.getText()+"','"+ txt_number.getText() +"')";
        
        Connection conn = dbConnection.connect();
        Statement st;
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        showStudents();
    }
    
    public void updateStudent(){
        String query = "UPDATE student SET name = '"+txt_name.getText()+"', number = '"+txt_number.getText()+"'WHERE student_id="+txt_id.getText();
        
        Connection conn = dbConnection.connect();
        Statement st;
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showStudents();
        
    }
    
    public void deleteStudent(){
        String query = "DELETE FROM student WHERE student_id="+txt_id.getText()+"";
        
        Connection conn = dbConnection.connect();
        Statement st;
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showStudents();
    }
    
    public void resetForm(){
      txt_id.setText(null);
      txt_name.setText(null);
      txt_number.setText(null);
    }
   
     public void switchToFinance() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/waliKelas/finance.fxml"));
        Stage window = (Stage)btn_finance.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    
    public void switchToInvoice() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/waliKelas/invoice.fxml"));
        Stage window = (Stage)btn_invoice.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    
}
