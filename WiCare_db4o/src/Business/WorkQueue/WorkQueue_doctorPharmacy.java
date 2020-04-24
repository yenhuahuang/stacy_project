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
public class WorkQueue_doctorPharmacy {
    private ArrayList<WorkRequest_doctorPharmacy> workRequestList;

    public WorkQueue_doctorPharmacy() {
        workRequestList = new ArrayList();
    }

    public ArrayList<WorkRequest_doctorPharmacy> getWorkRequestList() {
        return workRequestList;
    }
}
