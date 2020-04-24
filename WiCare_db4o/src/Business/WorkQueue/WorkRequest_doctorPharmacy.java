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
public class WorkRequest_doctorPharmacy {
    private UserAccount doctor;
    private UserAccount pharmacyReceiver;
    private People patient;
    private Date sendDate;
    private ArrayList<String> medicineArr;
    private String status;
    private String diagnosis;
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    

    public UserAccount getDoctor() {
        return doctor;
    }

    public void setDoctor(UserAccount doctor) {
        this.doctor = doctor;
    }

    public UserAccount getPharmacyReceiver() {
        return pharmacyReceiver;
    }

    public void setPharmacyReceiver(UserAccount pharmacyReceiver) {
        this.pharmacyReceiver = pharmacyReceiver;
    }

    public People getPatient() {
        return patient;
    }

    public void setPatient(People patient) {
        this.patient = patient;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public ArrayList<String> getMedicineArr() {
        return medicineArr;
    }

    public void setMedicineArr(ArrayList<String> medicineArr) {
        this.medicineArr = medicineArr;
    }
    
    @Override
    public String toString(){
        return patient.getName();
    }
}
