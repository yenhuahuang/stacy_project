/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.DocumentOrganization;
import Business.Organization.InvestigationOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.DocumentRole.DocumentWorkAreaJPanel;
import userinterface.InvestigationRole.CreateRelatedCaseJPanel;
import userinterface.InvestigationRole.MainInvestigationJPanel;

/**
 *
 * @author stacyhuang
 */
public class InvestigationRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business, Network network) {
        return new MainInvestigationJPanel(userProcessContainer, account, (InvestigationOrganization)organization, enterprise,business,network);
    }
}