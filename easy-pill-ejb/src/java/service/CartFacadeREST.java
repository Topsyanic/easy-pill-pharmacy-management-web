/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Cart;
import entities.Medicine;
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
import model.CartFacade;
import model.MedicineFacade;
import utilities.CartIdGenerator;
import utilities.RestMessage;

/**
 *
 * @author Topsy
 */
@Stateless
@Path("entities.cart")
public class CartFacadeREST extends AbstractFacade<Cart> {

    @EJB
    private CartFacade cartFacade;

    @EJB
    private CartIdGenerator cartIdGenerator;

    @EJB
    private MedicineFacade medicineFacade;

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    public CartFacadeREST() {
        super(Cart.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Cart entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Cart entity) {
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
    public Cart find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cart> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cart> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @POST
    @Path("{userId}/{medicineId}/{qty}")
    @Produces({MediaType.APPLICATION_JSON})
    public RestMessage addToCart(@PathParam("userId") String userId, @PathParam("medicineId") String medicineId, @PathParam("qty") int qty) {
        try {
            Medicine medicine = medicineFacade.find(medicineId);
            double price = Double.parseDouble(medicine.getPrice());
            Cart cart = new Cart();
            cart.setCartId(cartIdGenerator.generateNumber());
            cart.setMedicineId(medicineId);
            cart.setProductName(medicine.getName());
            cart.setUserId(userId);
            cart.setQuantity(qty);
            cart.setSubTotal(Double.toString(qty * price));
            create(cart);
            RestMessage message = new RestMessage("success");
            return message;
        } catch (NumberFormatException e) {
            System.out.println("Error " + e);
            RestMessage message = new RestMessage("failed");
            return message;
        }
    }

    @POST
    @Path("plus/{cartId}/{medicineId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cart increaseCartItem(@PathParam("cartId") String cartId, @PathParam("medicineId") String medicineId) {
        Medicine medicine = medicineFacade.find(medicineId);
        double price = Double.parseDouble(medicine.getPrice());
        Cart cart = cartFacade.find(cartId);
        int newQuantity = (cart.getQuantity() + 1);
        cart.setQuantity(newQuantity);
        cart.setSubTotal(Double.toString(newQuantity * price));
        em.merge(cart);
        return cart;
    }
    
    @POST
    @Path("minus/{cartId}/{medicineId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cart decreaseCartItem(@PathParam("cartId") String cartId, @PathParam("medicineId") String medicineId) {
        Medicine medicine = medicineFacade.find(medicineId);
        double price = Double.parseDouble(medicine.getPrice());
        Cart cart = cartFacade.find(cartId);
        int newQuantity = (cart.getQuantity() - 1);
        cart.setQuantity(newQuantity);
        cart.setSubTotal(Double.toString(newQuantity * price));
        em.merge(cart);
        return cart;
    }

    @POST
    @Path("remove/{cartId}")
    @Produces({MediaType.APPLICATION_JSON})
    public RestMessage removeFromCart(@PathParam("cartId") String cartId) {
        try {
            cartFacade.removeCartItem(cartId);
            RestMessage message = new RestMessage("success");
            return message;
        } catch (Exception e) {
            System.out.println("Error " + e);
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
