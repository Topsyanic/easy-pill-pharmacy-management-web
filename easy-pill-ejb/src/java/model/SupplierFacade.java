/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Supplier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Topsy
 */
@Stateless
public class SupplierFacade extends AbstractFacade<Supplier> {

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupplierFacade() {
        super(Supplier.class);
    }

    public Supplier getSupplierById(String supplierId) {
      Query q = em.createNamedQuery("Supplier.findBySupplierId");
        q.setParameter(1, supplierId);
        List<Supplier> a = q.getResultList();
        if (a.isEmpty()) {
            return null;
        } else {
            return a.get(0);
        }
    }

    public boolean updateAddress(String supplierId, String newAddress) {
      Query q = em.createNamedQuery("Supplier.updateAddress");
        q.setParameter(1, newAddress);
        q.setParameter(2, supplierId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean updateContactNo(String supplierId, String newContact) {
      Query q = em.createNamedQuery("Supplier.updateContactNo");
        q.setParameter(1, newContact);
        q.setParameter(2, supplierId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeSupplier(String supplierId) {
        Query q = em.createNamedQuery("Supplier.removeSupplier");
        q.setParameter(1, supplierId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkEmailExists(String email) {
      Query q = em.createNamedQuery("Supplier.findByEmail");
        q.setParameter(1, email);
        List a = q.getResultList();
        if (a.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    
}
