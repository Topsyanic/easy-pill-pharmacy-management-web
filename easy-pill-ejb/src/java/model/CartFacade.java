/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Cart;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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
        int newQuantity = (c.getQuantity() + 1);
        c.setQuantity(newQuantity);
        c.setSubTotal(Double.toString(newQuantity * price));
        em.merge(c);
    }

    public void reduceCartItem(String cartId, double price) {
        Cart c = em.find(Cart.class, cartId);
        int newQuantity = (c.getQuantity() - 1);
        c.setQuantity(newQuantity);
        c.setSubTotal(Double.toString(newQuantity * price));
        em.merge(c);
    }

    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public void disposeCart(String userId) {
        List<Cart> cartList = getUserCart(userId);
        if (!cartList.isEmpty()) {
            cartList.forEach((cart) -> {
                removeCartItem(cart.getCartId());
            });
        }
    }

}
