/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DT_ACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtAccounts.findAll", query = "SELECT d FROM DtAccounts d")
    , @NamedQuery(name = "DtAccounts.findByAccountNumber", query = "SELECT d FROM DtAccounts d WHERE d.accountNumber = :accountNumber")
    , @NamedQuery(name = "DtAccounts.findByAcctName", query = "SELECT d FROM DtAccounts d WHERE d.acctName = :acctName")
    , @NamedQuery(name = "DtAccounts.findByAcctBalance", query = "SELECT d FROM DtAccounts d WHERE d.acctBalance = :acctBalance")})
public class DtAccounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACCOUNT_NUMBER")
    private Integer accountNumber;
    @Basic(optional = false)
    @Column(name = "ACCT_NAME")
    private String acctName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "ACCT_BALANCE")
    private BigDecimal acctBalance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountNumber")
    private List<DtTransactions> dtTransactionsList;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne
    private DtUsers idUser;

    public DtAccounts() {
    }

    public DtAccounts(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public DtAccounts(Integer accountNumber, String acctName, BigDecimal acctBalance) {
        this.accountNumber = accountNumber;
        this.acctName = acctName;
        this.acctBalance = acctBalance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public BigDecimal getAcctBalance() {
        return acctBalance;
    }

    public void setAcctBalance(BigDecimal acctBalance) {
        this.acctBalance = acctBalance;
    }

    @XmlTransient
    public List<DtTransactions> getDtTransactionsList() {
        return dtTransactionsList;
    }

    public void setDtTransactionsList(List<DtTransactions> dtTransactionsList) {
        this.dtTransactionsList = dtTransactionsList;
    }

    public DtUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(DtUsers idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountNumber != null ? accountNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtAccounts)) {
            return false;
        }
        DtAccounts other = (DtAccounts) object;
        if ((this.accountNumber == null && other.accountNumber != null) || (this.accountNumber != null && !this.accountNumber.equals(other.accountNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return accountNumber.toString();
    }
    
}
