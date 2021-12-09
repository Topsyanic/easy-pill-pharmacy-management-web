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
@Table(name = "prescription")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prescription.findAll", query = "SELECT p FROM Prescription p")
    , @NamedQuery(name = "Prescription.findByPrescriptionId", query = "SELECT p FROM Prescription p WHERE p.prescriptionId = :prescriptionId")
    , @NamedQuery(name = "Prescription.findByImagePath", query = "SELECT p FROM Prescription p WHERE p.imagePath = :imagePath")
    , @NamedQuery(name = "Prescription.findByAddress", query = "SELECT p FROM Prescription p WHERE p.address = :address")
    , @NamedQuery(name = "Prescription.findByContact", query = "SELECT p FROM Prescription p WHERE p.contact = :contact")
    , @NamedQuery(name = "Prescription.findByUserId", query = "SELECT p FROM Prescription p WHERE p.userId = :userId")
    , @NamedQuery(name = "Prescription.findByStatus", query = "SELECT p FROM Prescription p WHERE p.status = :status")
    , @NamedQuery(name = "Prescription.findByDate", query = "SELECT p FROM Prescription p WHERE p.date = :date")
    , @NamedQuery(name = "Prescription.statusCount", query = "SELECT p FROM Prescription p WHERE p.status = ?1")
    , @NamedQuery(name = "Prescription.findByBillAmount", query = "SELECT p FROM Prescription p WHERE p.billAmount = :billAmount")})
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "prescription_id")
    private String prescriptionId;
    @Size(max = 500)
    @Column(name = "image_path")
    private String imagePath;
    @Size(max = 500)
    @Column(name = "address")
    private String address;
    @Size(max = 256)
    @Column(name = "contact")
    private String contact;
    @Size(max = 256)
    @Column(name = "user_id")
    private String userId;
    @Size(max = 256)
    @Column(name = "status")
    private String status;
    @Size(max = 256)
    @Column(name = "date")
    private String date;
    @Size(max = 256)
    @Column(name = "bill_amount")
    private String billAmount;

    public Prescription() {
    }

    public Prescription(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prescriptionId != null ? prescriptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.prescriptionId == null && other.prescriptionId != null) || (this.prescriptionId != null && !this.prescriptionId.equals(other.prescriptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prescription[ prescriptionId=" + prescriptionId + " ]";
    }

}
