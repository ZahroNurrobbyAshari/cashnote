/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package adminController;

import database.dbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import model.Incomes;
import model.Students;

/**
 * FXML Controller class
 *
 * @author zahro
 */
public class IncomeController implements Initializable {


    @FXML
    private Button btn_add;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_outcome;

    @FXML
    private Button btn_report;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_update;

    @FXML
    private ChoiceBox<String> choice_name;

    @FXML
    private TableColumn<Incomes, String> col_date;

    @FXML
    private TableColumn<Incomes, String> col_description;

    @FXML
    private TableColumn<Incomes, String> col_id;

    @FXML
    private TableColumn<Incomes, String> col_name;

    @FXML
    private TableColumn<Incomes, String> col_nominal;

    @FXML
    private TableColumn<Incomes, String> col_number;

    @FXML
    private DatePicker date;

    @FXML
    private TableView<Incomes> tableIncome;

    @FXML
    private TextField txt_desc;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_nominal;
    
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if (event.getSource()==btn_add) {
            insertIncome();
        }else if(event.getSource()==btn_update){
            updateIncome();
        }else if(event.getSource()==btn_delete){
            deleteIncome();
        }
        else if(event.getSource()==btn_reset){
            resetForm();
        }
    }
    
    @FXML
    public void handleMouseAction(MouseEvent event){
        Incomes income = tableIncome.getSelectionModel().getSelectedItem();
        var student_id = income.getStudent_id();
        txt_id.setText(income.getIncome_id());
        txt_nominal.setText(income.getNominal());
        txt_desc.setText(income.getDescription());
        date.setValue(LocalDate.parse(income.getDate()));
        choice_name.setValue("("+student_id.concat(") "+ income.getName()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        studentsInChoiceBox();
        showIncomes();
        
    }    
    
    private ObservableList<Incomes> getIncomesList(){
        ObservableList<Incomes> incomelist = FXCollections.observableArrayList();
        Connection conn = dbConnection.connect();
        Statement st;
        ResultSet rs;
        String query = "SELECT*FROM income JOIN student ON income.student_id=student.student_id";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {                
                incomelist.add(new Incomes(rs.getString("income_id"), rs.getString("student_id"),rs.getString("name"), rs.getString("number") , rs.getString("nominal"), rs.getString("date"), rs.getString("description"), rs.getString("created_at")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomelist;
    }
    
    private void showIncomes(){
        
        ObservableList list = getIncomesList();
        
        col_number.setCellValueFactory(new PropertyValueFactory<Incomes, String>("number"));
        col_nominal.setCellValueFactory(new PropertyValueFactory<Incomes, String>("nominal"));
        col_name.setCellValueFactory(new PropertyValueFactory<Incomes, String>("name"));
        col_date.setCellValueFactory(new PropertyValueFactory<Incomes, String>("date"));
        col_description.setCellValueFactory(new PropertyValueFactory<Incomes, String>("description"));
        col_id.setCellValueFactory(new PropertyValueFactory<Incomes, String>("income_id"));
        
        tableIncome.setItems(list);
    }
    
    private void insertIncome(){
        Connection conn = dbConnection.connect();
        Statement st;
        var student = choice_name.getValue();
        var student_id = student.substring(student.indexOf("(")+1, student.indexOf(")"));
        String nominal = txt_nominal.getText();
        String dateStr = date.getValue().toString();
        String desc = txt_desc.getText();
        String outcome = "0";
        String ref_type = "student";
        String query = "INSERT INTO income(student_id,nominal,date,description) "
                + "VALUES('"+student_id+"','"+nominal+"','"+dateStr+"','"+desc+"')";
        
        String query_archive = "INSERT INTO archive(income,outcome,description,ref_type,ref_id) VALUES('"+nominal+"','"+outcome+"','"+desc+"','"+ref_type+"','"+student_id+"')";
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query_archive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showIncomes();
    }
    
    private void updateIncome(){
        Connection conn = dbConnection.connect();
        Statement st;
        
        var student = choice_name.getValue();
        var student_id = student.substring(student.indexOf("(")+1, student.indexOf(")"));
        String nominal = txt_nominal.getText();
        String dateStr = date.getValue().toString();
        String desc = txt_desc.getText();
        String income_id = txt_id.getText();
       
        String query = "UPDATE income SET student_id = "+student_id+",nominal = '"+nominal+"',date='"+dateStr+"',description='"+desc+"' WHERE income_id= '"+income_id+"'" ;
       
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showIncomes();
    }
    
    private void deleteIncome(){
        Connection conn = dbConnection.connect();
        Statement st;
        
        String query = "DELETE FROM income WHERE income_id='"+txt_id.getText()+"'";
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showIncomes();
    }
    
    private void resetForm(){
        choice_name.setValue(null);
        txt_nominal.setText(null);
        txt_id.setText(null);
        txt_desc.setText(null);
        date.setValue(null);
    }
    
    private void studentsInChoiceBox(){
        Connection conn = dbConnection.connect();
        Statement st;
        
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            st=conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");
            while (rs.next()) {                
              String id = rs.getString("student_id");
              String name = rs.getString("name");
              choice_name.getItems().addAll("("+id.concat(") "+name));
              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }
    
    
    public void switchToDashboard() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/bendahara/dashboard.fxml"));
        Stage window = (Stage)btn_dashboard.getScene().getWindow();
           window.setScene(new Scene(root,1280,720));
           window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    

    public void switchToOutcome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bendahara/outcome.fxml"));
        Stage window = (Stage)btn_outcome.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    

    public void switchToReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bendahara/report.fxml"));
        Stage window = (Stage)btn_report.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    
    
    
    
}
