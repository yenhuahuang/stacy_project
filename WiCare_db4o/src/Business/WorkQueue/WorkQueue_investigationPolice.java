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
public class WorkQueue_investigationPolice {
    private ArrayList<WorkRequest_investigationPolice> workRequestList;

    public WorkQueue_investigationPolice() {
        workRequestList = new ArrayList();
    }

    public ArrayList<WorkRequest_investigationPolice> getWorkRequestList() {
        return workRequestList;
    }
}
