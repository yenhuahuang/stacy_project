/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Enterprise.Enterprise;
import People.People;

/**
 *
 * @author stacyhuang
 */
public class WorkRequest_investigationPolice {
    private People quarantinePeople;
    private String message;
    private String status;
    private String startdate;
    private String enddate;
    private Enterprise ent;
    private Enterprise sender;

   

    public People getQuarantinePeople() {
        return quarantinePeople;
    }

    public void setQuarantinePeople(People quarantinePeople) {
        this.quarantinePeople = quarantinePeople;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
     public Enterprise getEnt() {
        return ent;
    }

    public void setEnt(Enterprise ent) {
        this.ent = ent;
    }
     @Override
    public String toString(){
        return quarantinePeople.getId();
    }
    
}
