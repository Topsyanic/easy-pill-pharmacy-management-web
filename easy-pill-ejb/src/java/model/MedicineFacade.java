/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Medicine;
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
public class MedicineFacade extends AbstractFacade<Medicine> {

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicineFacade() {
        super(Medicine.class);
    }

    public List<Medicine> getAllMedicineInStock() {
        Query q = em.createNamedQuery("Medicine.findAllInStock");
        q.setParameter(1, "0");
        List<Medicine> a = q.getResultList();
        return a;
    }

    public List<Medicine> getAllMedicineOutOfStock() {
        Query q = em.createNamedQuery("Medicine.findAllOutOfStock");
        q.setParameter(1, "0");
        List<Medicine> a = q.getResultList();
        return a;
    }

    public Medicine getMedicineById(String medicineId) {
        Query q = em.createNamedQuery("Medicine.findByMedicineId");
        q.setParameter(1, medicineId);
        List<Medicine> a = q.getResultList();
        if (a.isEmpty()) {
            return null;
        } else {
            return a.get(0);
        }
    }

    public boolean updateName(String medicineId, String newName) {
        Query q = em.createNamedQuery("Medicine.updateName");
        q.setParameter(1, newName);
        q.setParameter(2, medicineId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateDescription(String medicineId, String newDesc) {
        Query q = em.createNamedQuery("Medicine.updateDescription");
        q.setParameter(1, newDesc);
        q.setParameter(2, medicineId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updatePrice(String medicineId, String newDesc) {
         Query q = em.createNamedQuery("Medicine.updatePrice");
        q.setParameter(1, newDesc);
        q.setParameter(2, medicineId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateQuantity(String medicineId, String newDesc) {
         Query q = em.createNamedQuery("Medicine.updateQuantity");
        q.setParameter(1, newDesc);
        q.setParameter(2, medicineId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeMedicine(String medicineId) {
        Query q = em.createNamedQuery("Medicine.removeMedicine");
        q.setParameter(1, medicineId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
