/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Topsy
 */
@Entity
@Table(name = "customer_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c")
    , @NamedQuery(name = "CustomerOrder.findByOrderId", query = "SELECT c FROM CustomerOrder c WHERE c.orderId = :orderId")
    , @NamedQuery(name = "CustomerOrder.findByUserId", query = "SELECT c FROM CustomerOrder c WHERE c.userId = :userId")
    , @NamedQuery(name = "CustomerOrder.findUserOrders", query = "SELECT c FROM CustomerOrder c WHERE c.userId = ?1")
    , @NamedQuery(name = "CustomerOrder.findByDate", query = "SELECT c FROM CustomerOrder c WHERE c.date = :date")
    , @NamedQuery(name = "CustomerOrder.statusCount", query = "SELECT c FROM CustomerOrder c WHERE c.status = ?1")
    , @NamedQuery(name = "CustomerOrder.findByDetails", query = "SELECT c FROM CustomerOrder c WHERE c.details = :details")
    , @NamedQuery(name = "CustomerOrder.findByAmount", query = "SELECT c FROM CustomerOrder c WHERE c.amount = :amount")
    , @NamedQuery(name = "CustomerOrder.findByStatus", query = "SELECT c FROM CustomerOrder c WHERE c.status = :status")})
public class CustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "order_id")
    private String orderId;
    @Size(max = 256)
    @Column(name = "user_id")
    private String userId;
    @Size(max = 5000)
    @Column(name = "details")
    private String details;
    @Size(max = 256)
    @Column(name = "amount")
    private String amount;
    @Size(max = 256)
    @Column(name = "status")
    private String status;
    @Size(max = 256)
    @Column(name = "date")
    private String date;

    public CustomerOrder() {
    }

    public CustomerOrder(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrder)) {
            return false;
        }
        CustomerOrder other = (CustomerOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CustomerOrder[ orderId=" + orderId + " ]";
    }

}
