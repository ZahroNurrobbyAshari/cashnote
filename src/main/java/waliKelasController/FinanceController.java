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
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Incomes;
import model.Outcomes;

/**
 * FXML Controller class
 *
 * @author zahro
 */
public class FinanceController implements Initializable {

    @FXML
    private TableColumn<Incomes, String> income_col_date;

    @FXML
    private TableColumn<Incomes, String> income_col_desc;

    @FXML
    private TableColumn<Incomes, String> income_col_id;

    @FXML
    private TableColumn<Incomes, String> income_col_name;

    @FXML
    private TableColumn<Incomes, String> income_col_nominal;

      @FXML
    private TableColumn<Outcomes, String> outcome_col_date;

    @FXML
    private TableColumn<Outcomes, String> outcome_col_desc;

    @FXML
    private TableColumn<Outcomes, String> outcome_col_id;

    @FXML
    private TableColumn<Outcomes, String> outcome_col_name;

    @FXML
    private TableColumn<Outcomes, String> outcome_col_nominal;
    
    @FXML
    private TableView<Incomes> tableIncome;

    @FXML
    private TableView<Outcomes> tableOutcome;
    
    @FXML
    private Button btn_students;
    
    @FXML
    private Button btn_invoice;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showIncomes();
        showOutcomes();
    }    
    
    private ObservableList<Incomes> getIncomeList(){
        ObservableList incomeList = FXCollections.observableArrayList();
        
         Connection conn = dbConnection.connect();
        Statement st;
        ResultSet rs;
        String query = "SELECT*FROM income JOIN student ON income.student_id=student.student_id";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {                
                incomeList.add(new Incomes(rs.getString("income_id"), rs.getString("student_id"),rs.getString("name"), rs.getString("number") , rs.getString("nominal"), rs.getString("date"), rs.getString("description"), rs.getString("created_at")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return incomeList;
    }
    
     private ObservableList<Outcomes> getOucomeList(){
        ObservableList outcomeList = FXCollections.observableArrayList();
        
        Connection conn = dbConnection.connect();
        Statement st;
        ResultSet rs;
        String query = "SELECT * FROM outcome";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while (rs.next()) {                
                outcomeList.add(new Outcomes(rs.getString("outcome_id"), rs.getString("name"), rs.getString("nominal"), rs.getString("date"), rs.getString("description")));
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return outcomeList;
    }
    
    private void showIncomes(){
        ObservableList list = getIncomeList();
        
        income_col_id.setCellValueFactory(new PropertyValueFactory<>("income_id"));
        income_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        income_col_nominal.setCellValueFactory(new PropertyValueFactory<>("nominal"));
        income_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        income_col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        tableIncome.setItems(list);
        
    }
    
    private void showOutcomes(){
        
        ObservableList list = getOucomeList();
        
        outcome_col_id.setCellValueFactory(new PropertyValueFactory<>("outcome_id"));
        outcome_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        outcome_col_nominal.setCellValueFactory(new PropertyValueFactory<>("nominal"));
        outcome_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        outcome_col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        tableOutcome.setItems(list);
        
    }
    
    public void switchToStudents() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/waliKelas/student.fxml"));
        Stage window = (Stage)btn_students.getScene().getWindow();
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
