/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Organization.DoctorOrganization;
import Business.Organization.DocumentOrganization;
import Business.UserAccount.UserAccount;
import People.People;
import java.util.Date;

/**
 *
 * @author stacyhuang
 */
public class WorkRequest_documentDoctor {
    private People people;
    private Date date;
    private DoctorOrganization receiver;
    private DocumentOrganization sender;
    private UserAccount assignedDoctor;
    private String status;

    public UserAccount getAssignedDoctor() {
        return assignedDoctor;
    }

    public void setAssignedDoctor(UserAccount assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DoctorOrganization getReceiver() {
        return receiver;
    }

    public void setReceiver(DoctorOrganization receiver) {
        this.receiver = receiver;
    }

    public DocumentOrganization getSender() {
        return sender;
    }

    public void setSender(DocumentOrganization sender) {
        this.sender = sender;
    }
    @Override
    public String toString(){
        return people.getName();
    }
}
