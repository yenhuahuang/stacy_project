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
public class WorkQueue_quarantineList {
    private ArrayList<WorkRequest_quarantineList> workRequestList;

    public WorkQueue_quarantineList() {
        workRequestList = new ArrayList();
    }

    public ArrayList<WorkRequest_quarantineList> getWorkRequestList() {
        return workRequestList;
    }
}
