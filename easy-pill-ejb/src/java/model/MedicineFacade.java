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
import utilities.Logged;


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

    @Logged
    public List<Medicine> getAllMedicineInStock() {
        Query q = em.createNamedQuery("Medicine.findAllInStock");
        q.setParameter(1, "0");
        List<Medicine> a = q.getResultList();
        return a;
    }

    @Logged
    public List<Medicine> getAllMedicineOutOfStock() {
        Query q = em.createNamedQuery("Medicine.findAllOutOfStock");
        q.setParameter(1, "0");
        List<Medicine> a = q.getResultList();
        return a;
    }

    @Logged
    public void updateName(String medicineId, String newName) {
        Medicine m = em.find(Medicine.class, medicineId);
        m.setName(newName);
        em.merge(m);
    }
    
    @Logged
    public void updateDescription(String medicineId, String description) {
        Medicine m = em.find(Medicine.class, medicineId);
        m.setDescription(description);
        em.merge(m);
    }

    @Logged    
    public void updatePrice(String medicineId, String price) {
        Medicine m = em.find(Medicine.class, medicineId);
        m.setPrice(price);
        em.merge(m);
    }
    
    @Logged
    public void updateQuantity(String medicineId, String quantity) {
        Medicine m = em.find(Medicine.class, medicineId);
        m.setQuantity(quantity);
        em.merge(m);
    }
    
    @Logged
    public void removeMedicine(String medicineId) {
        Medicine m = em.find(Medicine.class, medicineId);
        em.remove(em.merge(m));
    }

    @Logged
    public List<Medicine> searchMedicine(String keyword) {
      Query q = em.createNamedQuery("Medicine.searchMedicine");
        q.setParameter(1, "%"+keyword+"%");
        List<Medicine> a = q.getResultList();
        return a;
    }

}
