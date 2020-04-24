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
public class WorkQueue_quarantineCDC {
    private ArrayList<WorkRequest_quarantineCDC> workRequestList;

    public WorkQueue_quarantineCDC() {
        workRequestList = new ArrayList();
    }

    public ArrayList<WorkRequest_quarantineCDC> getWorkRequestList() {
        return workRequestList;
    }
}
