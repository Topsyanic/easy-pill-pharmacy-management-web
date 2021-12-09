/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Prescription;
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
public class PrescriptionFacade extends AbstractFacade<Prescription> {

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrescriptionFacade() {
        super(Prescription.class);
    }

    @Logged
    public int getPendingCount() {
        Query q = em.createNamedQuery("Prescription.statusCount");
        q.setParameter(1, "Pending");
        List<Prescription> a = q.getResultList();
        return a.size();
    }

    @Logged
    public int getConfirmedCount() {
        Query q = em.createNamedQuery("Prescription.statusCount");
        q.setParameter(1, "Confirmed");
        List<Prescription> a = q.getResultList();
        return a.size();
    }

    @Logged
    public void confirmPrescription(String prescriptionId, String amount) {
        Prescription p = em.find(Prescription.class, prescriptionId);
        p.setBillAmount(amount);
        p.setStatus("Confirmed");
        em.merge(p);
    }

    public void removePrescription(String prescriptionId) {
        Prescription p = em.find(Prescription.class, prescriptionId);
        em.remove(em.merge(p));
    }

}
