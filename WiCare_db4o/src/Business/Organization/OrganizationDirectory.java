/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(Type type){
        Organization organization = null;
        if (type.getValue().equals(Type.Doctor.getValue())){
            organization = new DoctorOrganization();
            organizationList.add(organization);
        }else if(type.getValue().equals(Type.Admin.getValue())){
            organization = new AdminOrganization();
            organizationList.add(organization);
        }else if(type.getValue().equals(Type.Document.getValue())){
            organization = new DocumentOrganization();
            organizationList.add(organization);
        }else if(type.getValue().equals(Type.Investigation.getValue())){
            organization = new InvestigationOrganization();
            organizationList.add(organization);
        }else if(type.getValue().equals(Type.Pharmacy.getValue())){
            organization = new PharmacyOrganization();
            organizationList.add(organization);
        }else if(type.getValue().equals(Type.Police.getValue())){
            organization = new PoliceOrganization();
            organizationList.add(organization);
        }else if(type.getValue().equals(Type.Quarantine.getValue())){
            organization = new QuarantineOrganization();
            organizationList.add(organization);
        }else if(type.getValue().equals(Type.Reception.getValue())){
            organization = new ReceptionOrganization();
            organizationList.add(organization);
        }
        else if(type.getValue().equals(Type.Population.getValue())){
            organization = new PopulationOrganization();
            organizationList.add(organization);
        }

        return organization;
    }
}