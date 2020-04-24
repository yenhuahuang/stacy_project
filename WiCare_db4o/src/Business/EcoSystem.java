/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Network.Network;
import Business.Organization.Organization;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import Business.WorkQueue.RegionData;
import MedicineCatelog.MedicineDirectory;
import People.PeopleDirectory;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class EcoSystem extends Organization{
    
    private static EcoSystem business;
    private PeopleDirectory peopleDirectory;
    //private MedicineDirectory medicineDir;
    private ArrayList<Network> networkList;
    private CountSymptoms countSymtoms;
    private CountAge countage;
    private int caseCount;
    private int quarantineCount;
    

    
  
    


    public static EcoSystem getBusiness() {
        return business;
    }

    public static void setBusiness(EcoSystem business) {
        EcoSystem.business = business;
    }

   
  
 

    public static EcoSystem getInstance(){
        if(business==null){
            business=new EcoSystem();
        }
        return business;
    }
    
    public Network createAndAddNetwork(){
        Network network=new Network();
        networkList.add(network);
        return network;
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList=new ArrayList<Role>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }
    private EcoSystem(){
        super(null);
        networkList=new ArrayList<Network>();
        peopleDirectory=new PeopleDirectory();
        //medicineDir=new MedicineDirectory();
        countage=new CountAge();
        countSymtoms=new CountSymptoms();
       
        this.caseCount=0;
        this.quarantineCount=0;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public int getQuarantineCount() {
        return quarantineCount;
    }

    public void setQuarantineCount(int quarantineCount) {
        this.quarantineCount = quarantineCount;
    }

    public CountSymptoms getCountSymtoms() {
        return countSymtoms;
    }

    public void setCountSymtoms(CountSymptoms countSymtoms) {
        this.countSymtoms = countSymtoms;
    }
     public CountAge getCountage() {
        return countage;
    }

    public void setCountage(CountAge countage) {
        this.countage = countage;
    }
    
   

    

    
//    public MedicineDirectory getMedicineDir() {
//        return medicineDir;
//    }
//
//    public void setMedicineDir(MedicineDirectory medicineDir) {
//        this.medicineDir = medicineDir;
//    }

    
    public PeopleDirectory getPeopleDirectory() {
        return peopleDirectory;
    }
    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public void setNetworkList(ArrayList<Network> networkList) {
        this.networkList = networkList;
    }
    
    public boolean checkIfUserIsUnique(String userName){
        if(!this.getUserAccountDirectory().checkIfUsernameIsUnique(userName)){
            return false;
        }
        for(Network network:networkList){
            
        }
        return true;
    }
    
  
}
