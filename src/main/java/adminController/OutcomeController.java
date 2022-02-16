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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Outcomes;

/**
 * FXML Controller class
 *
 * @author zahro
 */
public class OutcomeController implements Initializable {
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_income;

    @FXML
    private Button btn_report;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<Outcomes, String> col_date;

    @FXML
    private TableColumn<Outcomes, String> col_desc;

    @FXML
    private TableColumn<Outcomes, String> col_nominal;

    @FXML
    private TableColumn<Outcomes, String> col_number;

    @FXML
    private TableColumn<Outcomes, String> col_name;
    
    @FXML
    private DatePicker date;

    @FXML
    private TableView<Outcomes> tableOutcome;

    @FXML
    private TextField txt_desc;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_nominal;

    
    @FXML
    private ObservableList<Outcomes> getOutcomeList(){
        
        
        ObservableList<Outcomes> outcomeList = FXCollections.observableArrayList();
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
    
    @FXML
    private void showOutcome(){
        ObservableList list = getOutcomeList();
        
        col_number.setCellValueFactory(new PropertyValueFactory<>("outcome_id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nominal.setCellValueFactory(new PropertyValueFactory<>("nominal"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        tableOutcome.setItems(list);
        
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource()==btn_add){
            insertOutcome();
        }else if(event.getSource()==btn_update){
            updateOutcome();
        }else if(event.getSource()==btn_delete){
            deleteOutcome();
        }else if(event.getSource()==btn_reset){
            resetForm();
        }
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event){
        Outcomes outcome = tableOutcome.getSelectionModel().getSelectedItem();
        
        txt_name.setText(outcome.getName());
        txt_id.setText(outcome.getOutcome_id());
        txt_desc.setText(outcome.getDescription());
        txt_nominal.setText(outcome.getNominal());
        date.setValue(LocalDate.parse(outcome.getDate()));
        
    }
    
    private void resetForm(){
        date.setValue(null);
        txt_id.setText(null);
        txt_name.setText(null);
        txt_desc.setText(null);
        txt_nominal.setText(null);
    }
    
    private void insertOutcome(){
        Connection conn = dbConnection.connect();
        Statement st;
        
        String name= txt_name.getText();
        String nominal=  txt_nominal.getText();
        String description = txt_desc.getText();
        String dateStr = date.getValue().toString();
        String income = "0";
        String ref_type = "user";
        String ref_id= "1";
        String query = "INSERT INTO outcome(name,nominal,date,description) VALUES('"+name+"','"+nominal+"','"+dateStr+"','"+description+"')";
        
        String query_archive = "INSERT INTO archive(income,outcome,description,ref_type,ref_id,created_at) VALUES('"+income+"','"+nominal+"','"+description+"','"+ref_type+"','"+ref_id+"',NOW())";

        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query_archive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showOutcome();
    }
    
    private void updateOutcome(){
        
        Connection conn = dbConnection.connect();
        Statement st;
        
        String outcome_id = txt_id.getText();
        String name = txt_name.getText();
        String nominal = txt_nominal.getText();
        String dateStr = date.getValue().toString();
        String desc = txt_desc.getText();
       
        String query = "UPDATE outcome SET name='"+name+"',nominal='"+nominal+"',date='"+dateStr+"',description='"+desc+"' WHERE outcome_id='"+outcome_id+"'";
        
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showOutcome();
    }
    
    private void deleteOutcome(){
        Connection conn = dbConnection.connect();
        Statement st;
        String query = "DELETE FROM outcome WHERE outcome_id='"+txt_id.getText()+"'";

        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showOutcome();
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showOutcome();
    }    
    
    public void switchToDashboard() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/bendahara/dashboard.fxml"));
        Stage window = (Stage)btn_dashboard.getScene().getWindow();
        window.setScene(new Scene(root,1280,720));
        window.setMaximized(false);
        window.setMaximized(true);
        window.setResizable(false);
    }
    
   
    public void switchToIncome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bendahara/income.fxml"));
        Stage window = (Stage)btn_income.getScene().getWindow();        
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
