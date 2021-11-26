/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.User;
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
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public boolean checkEmailExists(String email) {
        Query q = em.createNamedQuery("User.findByEmail");
        q.setParameter(1, email);
        List a = q.getResultList();
        if (a.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public User getAuthenticatedUser(String email) {
        Query q = em.createNamedQuery("User.findByEmail");
        q.setParameter(1, email);
        List<User> a = q.getResultList();
        if (a.isEmpty()) {
            return null;
        } else {
            return a.get(0);
        }

    }

    public List<User> getAllCustomers() {
        Query q = em.createNamedQuery("User.findByUserRole");
        q.setParameter(1, "customer");
        List<User> a = q.getResultList();
        return a;
    }

    public List<User> getAllDoctors() {
        Query q = em.createNamedQuery("User.findByUserRole");
        q.setParameter(1, "doctor");
        List<User> a = q.getResultList();
        return a;
    }

    public List<User> getAllPharmacists() {
        Query q = em.createNamedQuery("User.findByUserRole");
        q.setParameter(1, "pharmacist");
        List<User> a = q.getResultList();
        return a;
    }

    public boolean removeUser(String userId) {
        Query q = em.createNamedQuery("User.removeByUserId");
        q.setParameter(1, userId);
        try {
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
