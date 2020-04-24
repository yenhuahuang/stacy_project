/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.UserAccount.UserAccount;
import People.People;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author stacyhuang
 */
public class WorkRequest_doctorQuarantine {
    private String testType;
    private String status;
    private Date date;
    private UserAccount doctor;
    private People patient;
    private String result;
    private UserAccount inspector;
    private int id;
    private ArrayList<String> symptoms;
    private String diagnosis;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public UserAccount getInspector() {
        return inspector;
    }

    public void setInspector(UserAccount inspector) {
        this.inspector = inspector;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserAccount getDoctor() {
        return doctor;
    }

    public void setDoctor(UserAccount doctor) {
        this.doctor = doctor;
    }

    public People getPatient() {
        return patient;
    }

    public void setPatient(People patient) {
        this.patient = patient;
    }
    
    @Override
    public String toString(){
        return String.valueOf(id);
    }
}
