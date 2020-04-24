/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MedicineCatelog;

import java.util.ArrayList;

/**
 *
 * @author stacyhuang
 */
public class MedicineDirectory {
    ArrayList<Medicine> medicines;
    public MedicineDirectory(){
        medicines=new ArrayList<Medicine>();
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }
    public Medicine createMedicineCatelog(String catelog){
        Medicine medicine=new Medicine(catelog);
        medicines.add(medicine);
        return medicine;
    }
    
}
