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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Topsy
 */
@Entity
@Table(name = "medicine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicine.findAll", query = "SELECT m FROM Medicine m")
    , @NamedQuery(name = "Medicine.findByMedicineId", query = "SELECT m FROM Medicine m WHERE m.medicineId = :medicineId")
    , @NamedQuery(name = "Medicine.findByName", query = "SELECT m FROM Medicine m WHERE m.name = :name")
    , @NamedQuery(name = "Medicine.findByDescription", query = "SELECT m FROM Medicine m WHERE m.description = :description")
    , @NamedQuery(name = "Medicine.findBySupplierId", query = "SELECT m FROM Medicine m WHERE m.supplierId = :supplierId")
    , @NamedQuery(name = "Medicine.findByWeight", query = "SELECT m FROM Medicine m WHERE m.weight = :weight")
    , @NamedQuery(name = "Medicine.findByPrice", query = "SELECT m FROM Medicine m WHERE m.price = :price")
    , @NamedQuery(name = "Medicine.findByImagePath", query = "SELECT m FROM Medicine m WHERE m.imagePath = :imagePath")
    , @NamedQuery(name = "Medicine.findByQuantity", query = "SELECT m FROM Medicine m WHERE m.quantity = :quantity")
    , @NamedQuery(name = "Medicine.findAllInStock", query = "SELECT m FROM Medicine m WHERE m.quantity > ?1 ")
    , @NamedQuery(name = "Medicine.findAllOutOfStock", query = "SELECT m FROM Medicine m WHERE m.quantity = ?1 ")
})
public class Medicine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "medicine_id")
    private String medicineId;
    @Size(max = 256)
    @Column(name = "name")
    private String name;
    @Size(max = 5000)
    @Column(name = "description")
    private String description;
    @Size(max = 256)
    @Column(name = "supplier_id")
    private String supplierId;
    @Size(max = 256)
    @Column(name = "weight")
    private String weight;
    @Size(max = 256)
    @Column(name = "price")
    private String price;
    @Size(max = 256)
    @Column(name = "image_path")
    private String imagePath;
    @Size(max = 256)
    @Column(name = "quantity")
    private String quantity;
    @Size(max = 256)
    @Column(name = "require_pres")
    private String requirePres;

    public Medicine() {
    }
    
    @PrePersist
    @PreUpdate
    private void validate(){
    if (medicineId == null || "".equals(medicineId))
        throw new IllegalArgumentException("Invalid medicine Id");
    if (name == null || "".equals(name))
        throw new IllegalArgumentException("Invalid name");
    if (description == null || "".equals(description))
        throw new IllegalArgumentException("Invalid description");
    if (supplierId == null || "".equals(supplierId))
        throw new IllegalArgumentException("Invalid supplier Id");
    if (weight == null || "".equals(weight))
        throw new IllegalArgumentException("Invalid weight");
    if (price == null || "".equals(price))
        throw new IllegalArgumentException("Invalid price");
    if (imagePath == null || "".equals(imagePath))
        throw new IllegalArgumentException("Invalid image path");
    if (quantity == null || "".equals(quantity))
        throw new IllegalArgumentException("Invalid quantity");
    if (requirePres == null || "".equals(requirePres))
        throw new IllegalArgumentException("Invalid require prescription");
    }

    public Medicine(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicineId != null ? medicineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicine)) {
            return false;
        }
        Medicine other = (Medicine) object;
        if ((this.medicineId == null && other.medicineId != null) || (this.medicineId != null && !this.medicineId.equals(other.medicineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Medicine[ medicineId=" + medicineId + " ]";
    }

    public String getRequirePres() {
        return requirePres;
    }

    public void setRequirePres(String requirePres) {
        this.requirePres = requirePres;
    }

}
