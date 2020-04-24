/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import People.People;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author stacyhuang
 */
public class QuarantineRelatedCase {
    private int caseNumber;
    private People quarantinePeople;
    private int relatedCaseNumber;
    private String startdate;
    private String enddate;
    private String status;
    private ArrayList<String> details;
    private Date sendDate;

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    

    public QuarantineRelatedCase(){
        details=new ArrayList();
    }
    public ArrayList<String> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    
    

    public int getRelatedCaseNumber() {
        return relatedCaseNumber;
    }

    public void setRelatedCaseNumber(int relatedCaseNumber) {
        this.relatedCaseNumber = relatedCaseNumber;
    }
    

    public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public People getQuarantinePeople() {
        return quarantinePeople;
    }

    public void setQuarantinePeople(People quarantinePeople) {
        this.quarantinePeople = quarantinePeople;
    }
    @Override
    public String toString(){
        return String.valueOf(caseNumber);
    }      
}
