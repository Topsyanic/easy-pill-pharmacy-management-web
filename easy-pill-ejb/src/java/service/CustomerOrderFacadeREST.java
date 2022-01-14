/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.CustomerOrder;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.CustomerOrderFacade;
import utilities.RestMessage;

/**
 *
 * @author Topsy
 */
@Stateless
@Path("entities.customerorder")
public class CustomerOrderFacadeREST extends AbstractFacade<CustomerOrder> {

    @EJB
    private CustomerOrderFacade customerOrderFacade;
    

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    public CustomerOrderFacadeREST() {
        super(CustomerOrder.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(CustomerOrder entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, CustomerOrder entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public CustomerOrder find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<CustomerOrder> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CustomerOrder> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @POST
    @Path("order/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RestMessage confirmOrder(@PathParam("id") String orderId) {
        try {
            CustomerOrder c = em.find(CustomerOrder.class, orderId);
            c.setStatus("Confirmed");
            em.merge(c);
            RestMessage message = new RestMessage("success");
            return message;
        } catch (Exception e) {
            RestMessage message = new RestMessage("failed");
            return message;
        }

    }
    
    @POST
    @Path("pay/{details}/{amount}/{userId}/{address}")
    @Produces({MediaType.APPLICATION_JSON})
    public RestMessage makeOrder(@PathParam("details") String details, @PathParam("amount") String amount, @PathParam("userId") String userId, @PathParam("address") String address) {
        try {
            customerOrderFacade.mobileOrder(details, amount, userId,address);
            RestMessage message = new RestMessage("success");
            return message;
        } catch (Exception e) {
            RestMessage message = new RestMessage("failed");
            return message;
        }
    }
    
    @GET
    @Path("userorders/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CustomerOrder> getUserOrders( @PathParam("userId") String userId) {
         return  customerOrderFacade.getUserPendingOrders(userId);
    }
    @GET
    @Path("userhistory/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CustomerOrder> getUserHistory( @PathParam("userId") String userId) {
         return  customerOrderFacade.getUserHistory(userId);
    }
    
    @POST
    @Path("cancel/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public RestMessage cancelOrder(@PathParam("id") String orderId) {
        try {
            customerOrderFacade.cancelOrder(orderId);
            RestMessage message = new RestMessage("success");
            return message;
        } catch (Exception e) {
            RestMessage message = new RestMessage("failed");
            return message;
        }

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
