/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import MedicineCatelog.MedicineDirectory;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class EnterpriseDirectory {
    private ArrayList<Enterprise> enterpriseList;
    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(ArrayList<Enterprise> enterpriseList) {
        this.enterpriseList = enterpriseList;
    }
    
    public EnterpriseDirectory(){
        enterpriseList=new ArrayList<Enterprise>();
    }
    
    //Create enterprise
    public Enterprise createAndAddEnterprise(String name,Enterprise.EnterpriseType type){
        Enterprise enterprise=null;
        if(type==Enterprise.EnterpriseType.Hospital){
            enterprise=new HospitalEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Doctor);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Document);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Pharmacy);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Quarantine);
            enterpriseList.add(enterprise);
        }else if(type==Enterprise.EnterpriseType.CDC){
            enterprise=new CDCEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Investigation);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Publish);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Reception);
            enterpriseList.add(enterprise);
        }else if(type==Enterprise.EnterpriseType.Police){
            enterprise=new PoliceEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Police);
            enterpriseList.add(enterprise);
        }
        else if(type==Enterprise.EnterpriseType.Population){
            enterprise=new PoliceEnterprise(name);
            enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Population);
            enterpriseList.add(enterprise);
        }
        return enterprise;
    }
}
