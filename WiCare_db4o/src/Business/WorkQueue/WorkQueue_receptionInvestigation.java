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
public class WorkQueue_receptionInvestigation {
    private ArrayList<WorkRequest_receptionInvestigation> workRequestList;

    public WorkQueue_receptionInvestigation() {
        workRequestList = new ArrayList();
    }

    public ArrayList<WorkRequest_receptionInvestigation> getWorkRequestList() {
        return workRequestList;
    }
}
