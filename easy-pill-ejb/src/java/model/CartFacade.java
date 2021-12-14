/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Cart;
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
public class CartFacade extends AbstractFacade<Cart> {

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartFacade() {
        super(Cart.class);
    }

    @Logged
    public List<Cart> getUserCart(String userId) {
        Query q = em.createNamedQuery("Cart.findUserCart");
        q.setParameter(1, userId);
        List<Cart> a = q.getResultList();
        return a;
    }

    public void removeCartItem(String cartId) {
        Cart c = em.find(Cart.class, cartId);
        em.remove(em.merge(c));
    }

    public void increaseCartItem(String cartId, double price) {
        Cart c = em.find(Cart.class, cartId);
        int newQuantity = (Integer.parseInt(c.getQuantity()) + 1);
        c.setQuantity(String.valueOf(newQuantity));
        c.setSubTotal(Double.toString(newQuantity*price));
        em.merge(c);
    }

    public void reduceCartItem(String cartId, double price) {
        Cart c = em.find(Cart.class, cartId);
        int newQuantity = (Integer.parseInt(c.getQuantity()) - 1);
        c.setQuantity(String.valueOf(newQuantity));
        c.setSubTotal(Double.toString(newQuantity*price));
        em.merge(c);
    }

}
