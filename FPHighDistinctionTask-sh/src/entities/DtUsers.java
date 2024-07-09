/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan David
 */
@Entity
@Table(name = "DT_USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtUsers.findAll", query = "SELECT d FROM DtUsers d")
    , @NamedQuery(name = "DtUsers.findByIdUser", query = "SELECT d FROM DtUsers d WHERE d.idUser = :idUser")
    , @NamedQuery(name = "DtUsers.findByFirstName", query = "SELECT d FROM DtUsers d WHERE d.firstName = :firstName")
    , @NamedQuery(name = "DtUsers.findByLastName", query = "SELECT d FROM DtUsers d WHERE d.lastName = :lastName")
    , @NamedQuery(name = "DtUsers.findByEmail", query = "SELECT d FROM DtUsers d WHERE d.email = :email")
    , @NamedQuery(name = "DtUsers.findByPassword", query = "SELECT d FROM DtUsers d WHERE d.password = :password")
    , @NamedQuery(name = "DtUsers.findByUserType", query = "SELECT d FROM DtUsers d WHERE d.userType = :userType")
    , @NamedQuery(name = "DtUsers.findByAddress", query = "SELECT d FROM DtUsers d WHERE d.address = :address")
    , @NamedQuery(name = "DtUsers.findByPhoneNumber", query = "SELECT d FROM DtUsers d WHERE d.phoneNumber = :phoneNumber")})
public class DtUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private String idUser;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "USER_TYPE")
    private String userType;
    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<DtTransactions> dtTransactionsList;
    @OneToMany(mappedBy = "idUser")
    private List<DtAccounts> dtAccountsList;

    public DtUsers() {
    }

    public DtUsers(String idUser) {
        this.idUser = idUser;
    }

    public DtUsers(String idUser, String firstName, String lastName, String email, String password, String userType, String address, String phoneNumber) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlTransient
    public List<DtTransactions> getDtTransactionsList() {
        return dtTransactionsList;
    }

    public void setDtTransactionsList(List<DtTransactions> dtTransactionsList) {
        this.dtTransactionsList = dtTransactionsList;
    }

    @XmlTransient
    public List<DtAccounts> getDtAccountsList() {
        return dtAccountsList;
    }

    public void setDtAccountsList(List<DtAccounts> dtAccountsList) {
        this.dtAccountsList = dtAccountsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtUsers)) {
            return false;
        }
        DtUsers other = (DtUsers) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DtUsers[ idUser=" + idUser + " ]";
    }
    
}
