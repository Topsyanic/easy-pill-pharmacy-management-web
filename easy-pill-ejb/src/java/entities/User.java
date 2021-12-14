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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId")
    , @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = ?1")
    , @NamedQuery(name = "User.removeByUserId", query = "DELETE FROM User u WHERE u.userId = ?1")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByContactNo", query = "SELECT u FROM User u WHERE u.contactNo = :contactNo")
    , @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address")
    , @NamedQuery(name = "User.findByExpertise", query = "SELECT u FROM User u WHERE u.expertise = :expertise")
    , @NamedQuery(name = "User.findByUserRole", query = "SELECT u FROM User u WHERE u.userRole = ?1")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "user_id")
    private String userId;
    @Size(max = 256)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 256)
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 256)
    @Column(name = "email")
    private String email;
    @Size(max = 256)
    @Column(name = "password")
    private String password;
    @Size(max = 256)
    @Column(name = "contact_no")
    private String contactNo;
    @Size(max = 256)
    @Column(name = "address")
    private String address;
    @Size(max = 256)
    @Column(name = "expertise")
    private String expertise;
    @Size(max = 256)
    @Column(name = "user_role")
    private String userRole;

    public User() {
    }

    @PrePersist
    @PreUpdate
    private void validate() {
        if (userId == null || "".equals(userId)) {
            throw new IllegalArgumentException("Invalid user id");
        }
        if (firstName == null || "".equals(firstName)) {
            throw new IllegalArgumentException("Invalid first name");
        }
        if (lastName == null || "".equals(lastName)) {
            throw new IllegalArgumentException("Invalid last name");
        }
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (password == null || "".equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (contactNo == null || "".equals(contactNo)) {
            throw new IllegalArgumentException("Invalid contact no");
        }
        if (address == null || "".equals(address)) {
            throw new IllegalArgumentException("Invalid address");
        }
        if (expertise == null || "".equals(expertise)) {
            throw new IllegalArgumentException("Invalid expertise");
        }
        if (userRole == null || "".equals(userRole)) {
            throw new IllegalArgumentException("Invalid user role");
        }

    }

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ userId=" + userId + " ]";
    }

}
