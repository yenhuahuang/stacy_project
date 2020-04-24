/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.DocumentRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author stacyhuang
 */
public class DocumentOrganization extends Organization{
    public DocumentOrganization() {
        super(Organization.Type.Document.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new DocumentRole());
        return roles;
    }
}
