/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.CustomerOrder;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import utilities.Logged;
import utilities.OrderIdGenerator;

/**
 *
 * @author Topsy
 */
@Stateless
public class CustomerOrderFacade extends AbstractFacade<CustomerOrder> {

    @EJB
    private OrderIdGenerator orderIdGenerator;

    @EJB
    private CartFacade cartFacade;

    @PersistenceContext(unitName = "easy-pill-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderFacade() {
        super(CustomerOrder.class);
    }

    @Logged
    public List<CustomerOrder> getUserOrders(String userId) {
        Query q = em.createNamedQuery("CustomerOrder.findUserOrders");
        q.setParameter(1, userId);
        List<CustomerOrder> a = q.getResultList();
        return a;
    }
    
    @Logged
    public List<CustomerOrder> getNewOrders() {
        Query q = em.createNamedQuery("CustomerOrder.findAll");
        List<CustomerOrder> a = q.setMaxResults(10).getResultList();
        return a;
    }

    @Logged
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public void makeOrder(String orderDetails, String amount, String userId) {
        CustomerOrder orderItem = new CustomerOrder();
        orderItem.setOrderId(orderIdGenerator.generateNumber());
        orderItem.setUserId(userId);
        orderItem.setDate(LocalDate.now().toString());
        orderItem.setDetails(orderDetails);
        orderItem.setAmount(amount);
        orderItem.setStatus("Pending");
        create(orderItem);
        cartFacade.disposeCart(userId);
    }

    @Logged
    public void cancelOrder(String orderId) {
        CustomerOrder c = em.find(CustomerOrder.class, orderId);
        em.remove(em.merge(c));
    }

    @Logged
    public Object getPendingCount() {
        Query q = em.createNamedQuery("CustomerOrder.statusCount");
        q.setParameter(1, "Pending");
        List<CustomerOrder> a = q.getResultList();
        return a.size();
    }

    @Logged
    public Object getConfirmedCount() {
        Query q = em.createNamedQuery("CustomerOrder.statusCount");
        q.setParameter(1, "Confirmed");
        List<CustomerOrder> a = q.getResultList();
        return a.size();
    }

    @Logged
    public void confirmOrder(String orderId) {
        CustomerOrder c = em.find(CustomerOrder.class, orderId);
        c.setStatus("Confirmed");
        em.merge(c);
    }

}
