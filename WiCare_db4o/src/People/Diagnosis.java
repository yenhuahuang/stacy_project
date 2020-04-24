/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import java.util.ArrayList;

/**
 *
 * @author stacyhuang
 */
public class Diagnosis {
    private String date;
    private String diagnosis;
    private ArrayList<String> medicine;
    private ArrayList<String> symptoms;
    
    public Diagnosis(){
        medicine=new ArrayList();
        symptoms=new ArrayList();
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public ArrayList<String> getMedicine() {
        return medicine;
    }

    public void setMedicine(ArrayList<String> medicine) {
        this.medicine = medicine;
    }
    
  @Override
    public String toString(){
        return date;
    }
    
}
