/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.PopulationRole;
import Business.Role.QuarantineRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class PopulationOrganization extends Organization{
    public PopulationOrganization() {
        super(Organization.Type.Population.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new PopulationRole());
        return roles;
    }
}