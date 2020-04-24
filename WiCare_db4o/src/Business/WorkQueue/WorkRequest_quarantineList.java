/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import People.People;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author stacyhuang
 */
public class WorkRequest_quarantineList {
    private int caseNumber;
    private People patient;
    private Date diagnosisDate;
    private Date sentDate;
    private ArrayList<QuarantineRelatedCase> relatedCase;

    public WorkRequest_quarantineList(){
        relatedCase=new ArrayList();
    }
    public ArrayList<QuarantineRelatedCase> getRelatedCase() {
        return relatedCase;
    }

    public void setRelatedCase(ArrayList<QuarantineRelatedCase> relatedCase) {
        this.relatedCase = relatedCase;
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

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
    @Override
    public String toString(){
        return String.valueOf(caseNumber);
    }
}
