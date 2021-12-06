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
@Table(name = "inventory_requests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventoryRequests.findAll", query = "SELECT i FROM InventoryRequests i")
    , @NamedQuery(name = "InventoryRequests.findByRequestId", query = "SELECT i FROM InventoryRequests i WHERE i.requestId = :requestId")
    , @NamedQuery(name = "InventoryRequests.findByMessage", query = "SELECT i FROM InventoryRequests i WHERE i.message = :message")
    , @NamedQuery(name = "InventoryRequests.findByDate", query = "SELECT i FROM InventoryRequests i WHERE i.date = :date")
    , @NamedQuery(name = "InventoryRequests.findBySupplierId", query = "SELECT i FROM InventoryRequests i WHERE i.supplierId = :supplierId")})
public class InventoryRequests implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "request_id")
    private String requestId;
    @Size(max = 1024)
    @Column(name = "message")
    private String message;
    @Size(max = 256)
    @Column(name = "date")
    private String date;
    @Size(max = 256)
    @Column(name = "supplier_id")
    private String supplierId;

    public InventoryRequests() {
    }

    public InventoryRequests(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryRequests)) {
            return false;
        }
        InventoryRequests other = (InventoryRequests) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InventoryRequests[ requestId=" + requestId + " ]";
    }
    
}
