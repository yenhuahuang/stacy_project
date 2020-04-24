/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.ArrayList;

/**
 *
 * @author stacyhuang
 */
public class WorkQueue_documentDoctor {
    private ArrayList<WorkRequest_documentDoctor> workRequestList;

    public WorkQueue_documentDoctor() {
        workRequestList = new ArrayList();
    }

    public ArrayList<WorkRequest_documentDoctor> getWorkRequestList() {
        return workRequestList;
    }
}
