/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import Business.WorkQueue.WorkQueue_doctorQuarantine;
import Business.WorkQueue.WorkQueue_quarantineList;
import Business.WorkQueue.WorkRequest_doctorQuarantine;

/**
 *
 * @author stacyhuang
 */
public class People {
    private String id;
    private String picture;
    private String name;
    private int age;
    private String birthday;
    private String phone;
    private String address;
    private String county;
    private String state;
    private String gender;
   private WorkQueue_doctorQuarantine quarantineTesting;
    private MedicalRecord medicalRecord;
    public People(){
        this.medicalRecord=new MedicalRecord();
    }
    public People(String id,String name, String birthday,int age,String phone,String address,String county,String state,String gender,String picture){
        this.medicalRecord=new MedicalRecord();
        this.id=id;
        this.name=name;
        this.address=address;
        this.age=age;
        this.phone=phone;
        this.county=county;
        this.state=state;
        this.picture=picture; 
        this.birthday=birthday;
        this.gender=gender;
        quarantineTesting=new WorkQueue_doctorQuarantine();  
    }

    
    public WorkQueue_doctorQuarantine getQuarantineTesting() {
        return quarantineTesting;
    }

    public void setQuarantineTesting(WorkQueue_doctorQuarantine quarantineTesting) {
        this.quarantineTesting = quarantineTesting;
    }

    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString(){
        return name;
    }
}
