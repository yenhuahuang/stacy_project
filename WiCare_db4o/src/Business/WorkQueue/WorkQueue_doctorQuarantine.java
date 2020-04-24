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
public class WorkQueue_doctorQuarantine {
    private ArrayList<WorkRequest_doctorQuarantine> workRequestList;

    public WorkQueue_doctorQuarantine() {
        workRequestList = new ArrayList();
    }

    public ArrayList<WorkRequest_doctorQuarantine> getWorkRequestList() {
        return workRequestList;
    }
}
