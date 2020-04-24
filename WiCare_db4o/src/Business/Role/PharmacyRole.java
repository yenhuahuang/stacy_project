/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.PharmacyOrganization;
import Business.UserAccount.UserAccount;
import MedicineCatelog.Medicine;
import javax.swing.JPanel;
import userinterface.DocumentRole.DocumentWorkAreaJPanel;
import userinterface.PharmacyRole.DescriptionJPanel;
import userinterface.PharmacyRole.PharmacyWorkAreaJpanel;

/**
 *
 * @author stacyhuang
 */
public class PharmacyRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, Network network) {
        return new PharmacyWorkAreaJpanel(userProcessContainer, account, (PharmacyOrganization)organization, enterprise,business, network);
   }
    
}
