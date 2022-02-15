/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zahro
 */
public class Outcomes {
 
    String outcome_id,name,nominal,date,description;

    public Outcomes(String outcome_id, String name, String nominal, String date, String description) {
        this.outcome_id = outcome_id;
        this.name = name;
        this.nominal = nominal;
        this.date = date;
        this.description = description;
    }

    public String getOutcome_id() {
        return outcome_id;
    }

    public void setOutcome_id(String outcome_id) {
        this.outcome_id = outcome_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
