/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utilities.UserEmailValidator;
import utilities.RestMessage;

/**
 *
 * @author Topsy
 */
@Stateless
@Path("entities.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @EJB
    private UserEmailValidator userEmailValidator;

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, User entity) {
        super.edit(entity);
    }

    @POST
    @Path("delete/{id}")
    public RestMessage remove(@PathParam("id") String id) {
        try{
             super.remove(super.find(id));
             RestMessage message = new RestMessage("success");
             return message;
        }
        catch(Exception e){
            RestMessage message = new RestMessage("failed");
             return message;
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Path("user/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public User findByEmail(@PathParam("email") String email) {
        Query q = em.createNamedQuery("User.findByEmail");
        q.setParameter(1, email);
        List<User> a = q.getResultList();
        return a.get(0);
    }
    
    @GET
    @Path("customers")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllCustomers() {
         Query q = em.createNamedQuery("User.findByUserRole");
        q.setParameter(1, "customer");
        List<User> a = q.getResultList();
        return a;
    }
    @GET
    @Path("doctors")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllDoctors() {
         Query q = em.createNamedQuery("User.findByUserRole");
        q.setParameter(1, "doctor");
        List<User> a = q.getResultList();
        return a;
    }
    @GET
    @Path("pharmacists")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getAllPharmacists() {
         Query q = em.createNamedQuery("User.findByUserRole");
        q.setParameter(1, "pharmacist");
        List<User> a = q.getResultList();
        return a;
    }

    @GET
    @Path("mailcheck/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public RestMessage checkEmail(@PathParam("email") String email) {
        if (userEmailValidator.emailExists(email)) {
            RestMessage message = new RestMessage("invalid");
            return message;
        } else {
            RestMessage message = new RestMessage("valid");
            return message;
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
