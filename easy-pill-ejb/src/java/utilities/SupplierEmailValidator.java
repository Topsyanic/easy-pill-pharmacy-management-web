/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import entities.Supplier;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import model.SupplierFacade;

/**
 *
 * @author Topsy
 */
@Stateless
@LocalBean
public class SupplierEmailValidator implements EmailValidator {

    @EJB
    private SupplierFacade supplierFacade;
    
    
    @Override
    public boolean emailExists(String email) {
        List<Supplier> supplierList = supplierFacade.getSuppliersByEmail(email);
        if (supplierList.isEmpty()) {
            return false;
        } else {
            return true;
        }
        
    }

}
