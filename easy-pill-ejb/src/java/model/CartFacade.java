/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Cart;
import entities.Medicine;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;
import javax.ejb.EJB;
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

    @EJB
    private MedicineFacade medicineFacade;

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

    @Logged
    public void removeCartItem(String cartId) {
        Cart c = em.find(Cart.class, cartId);
        em.remove(em.merge(c));
    }

    @Logged
    public void increaseCartItem(String cartId, double price) {
        Cart c = em.find(Cart.class, cartId);
        int newQuantity = (c.getQuantity() + 1);
        c.setQuantity(newQuantity);
        c.setSubTotal(Double.toString(newQuantity * price));
        em.merge(c);
    }

    @Logged
    public void reduceCartItem(String cartId, double price) {
        Cart c = em.find(Cart.class, cartId);
        int newQuantity = (c.getQuantity() - 1);
        c.setQuantity(newQuantity);
        c.setSubTotal(Double.toString(newQuantity * price));
        em.merge(c);
    }

    @Logged
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public void updateStock(String userId) {
        List<Cart> cartList = getUserCart(userId);
        if (!cartList.isEmpty()) {
            cartList.forEach((cart) -> {
                Medicine m = em.find(Medicine.class, cart.getMedicineId());
                int qty = Integer.parseInt(m.getQuantity()) - cart.getQuantity();
                m.setQuantity(String.valueOf(qty));
                em.merge(m);
            });
        }
    }

    @Logged
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
