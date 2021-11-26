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
        List a =  q.getResultList();
        if (a.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public User getAuthenticatedUser(String email) {
        Query q = em.createNamedQuery("User.findByEmail");
        q.setParameter(1, email);
        List<User> a =  q.getResultList();
        if(a.isEmpty()){
            return null;
        }
        else
        {
            return a.get(0);
        }
        
    }

}
