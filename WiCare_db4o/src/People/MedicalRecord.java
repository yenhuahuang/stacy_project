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
public class MedicalRecord {
    private String bloodType;
    private String emergency_contactName;
    private String emergency_contactPhone;
    private String pregancy;
     private double height;
    private double weight;
    private ArrayList<String> past_history;
    private ArrayList<String> family_history;
    private ArrayList<String> operation_history;
    private ArrayList<String> chronicDisease;
    private ArrayList<String> medicineAllergy;
    private ArrayList<Diagnosis> doctorNote;
    private ArrayList<String> testingRecord;
    
//
//    public MedicalRecord(String bloodType,String emergency_contactName,String emergency_contactPhone,String pregnancy,ArrayList<String> past_history,ArrayList<String> family_history,ArrayList<String> operation_history,ArrayList<String> chronicdiease){
//        this.bloodType=bloodType;
//        this.emergency_contactName=emergency_contactName;
//        this.emergency_contactPhone=emergency_contactPhone;
//        this.pregancy=pregnancy;
//        this.past_history=new ArrayList<String>();
//        this.family_history=new ArrayList<String>();
//        this.operation_history=new ArrayList<String>();
//        this.chronicDisease=new ArrayList<String>();
//        
//        this.doctorNote=new ArrayList<String>();
//        this.medicineRecord=new ArrayList<String>();
//        this.testingRecord=new ArrayList<String>();
//    }
    public MedicalRecord(){
        this.past_history=new ArrayList<String>();
        this.family_history=new ArrayList<String>();
        this.operation_history=new ArrayList<String>();
        this.chronicDisease=new ArrayList<String>();
        this.doctorNote=new ArrayList<Diagnosis>();
        //this.medicineRecord=new ArrayList<String>();
        this.testingRecord=new ArrayList<String>();
        this.medicineAllergy=new ArrayList<String>();
        this.pregancy="";
        this.emergency_contactName="";
        this.emergency_contactPhone="";
        this.bloodType="";
        this.height=0.0;
        this.weight=0.0;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ArrayList<String> getMedicineAllergy() {
        return medicineAllergy;
    }

    public void setMedicineAllergy(ArrayList<String> medicineAllergy) {
        this.medicineAllergy = medicineAllergy;
    }
    
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmergency_contactName() {
        return emergency_contactName;
    }

    public void setEmergency_contactName(String emergency_contactName) {
        this.emergency_contactName = emergency_contactName;
    }

    public String getEmergency_contactPhone() {
        return emergency_contactPhone;
    }

    public void setEmergency_contactPhone(String emergency_contactPhone) {
        this.emergency_contactPhone = emergency_contactPhone;
    }

    public ArrayList<String> getPast_history() {
        return past_history;
    }

    public void setPast_history(ArrayList<String> past_history) {
        this.past_history = past_history;
    }

    public ArrayList<String> getFamily_history() {
        return family_history;
    }

    public void setFamily_history(ArrayList<String> family_history) {
        this.family_history = family_history;
    }

    public ArrayList<String> getOperation_history() {
        return operation_history;
    }

    public void setOperation_history(ArrayList<String> operation_history) {
        this.operation_history = operation_history;
    }

    public ArrayList<String> getChronicDisease() {
        return chronicDisease;
    }

    public void setChronicDisease(ArrayList<String> chronicDisease) {
        this.chronicDisease = chronicDisease;
    }

    public ArrayList<Diagnosis> getDoctorNote() {
        return doctorNote;
    }

    public void setDoctorNote(ArrayList<Diagnosis> doctorNote) {
        this.doctorNote = doctorNote;
    }

//    public ArrayList<String> getMedicineRecord() {
//        return medicineRecord;
//    }
//
//    public void setMedicineRecord(ArrayList<String> medicineRecord) {
//        this.medicineRecord = medicineRecord;
//    }

    public ArrayList<String> getTestingRecord() {
        return testingRecord;
    }

    public void setTestingRecord(ArrayList<String> testingRecord) {
        this.testingRecord = testingRecord;
    }

    public String getPregancy() {
        return pregancy;
    }

    public void setPregancy(String pregancy) {
        this.pregancy = pregancy;
    }
    
}
