/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Enterprise.Enterprise;
import Business.UserAccount.UserAccount;
import People.People;
import java.util.Date;

/**
 *
 * @author stacyhuang
 */
public class WorkRequest_quarantineCDC {
    private int caseNumber;
    private People patient;
    private Enterprise enterprise;
    private String status;
    private Date date;
    private String symptons;
    private UserAccount receiver;
    private String message;
    private Enterprise sendtoEnterprise;

    public Enterprise getSendtoEnterprise() {
        return sendtoEnterprise;
    }

    public void setSendtoEnterprise(Enterprise sendtoEnterprise) {
        this.sendtoEnterprise = sendtoEnterprise;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(UserAccount receiver) {
        this.receiver = receiver;
    }

    public String getSymptons() {
        return symptons;
    }

    public void setSymptons(String symptons) {
        this.symptons = symptons;
    }

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public People getPatient() {
        return patient;
    }

    public void setPatient(People patient) {
        this.patient = patient;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
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
    @Override 
    public String toString(){
        return String.valueOf(caseNumber);
    }
}
