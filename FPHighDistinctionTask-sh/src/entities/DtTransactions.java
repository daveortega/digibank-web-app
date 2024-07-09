/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David
 */
@Entity
@Table(name = "DT_TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtTransactions.findAll", query = "SELECT d FROM DtTransactions d")
    , @NamedQuery(name = "DtTransactions.findByTransactionId", query = "SELECT d FROM DtTransactions d WHERE d.transactionId = :transactionId")
    , @NamedQuery(name = "DtTransactions.findByTransactionName", query = "SELECT d FROM DtTransactions d WHERE d.transactionName = :transactionName")
    , @NamedQuery(name = "DtTransactions.findByDescription", query = "SELECT d FROM DtTransactions d WHERE d.description = :description")
    , @NamedQuery(name = "DtTransactions.findByLocation", query = "SELECT d FROM DtTransactions d WHERE d.location = :location")
    , @NamedQuery(name = "DtTransactions.findByAmount", query = "SELECT d FROM DtTransactions d WHERE d.amount = :amount")
    , @NamedQuery(name = "DtTransactions.findByDatetimeTrans", query = "SELECT d FROM DtTransactions d WHERE d.datetimeTrans = :datetimeTrans")})
public class DtTransactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;
    @Basic(optional = false)
    @Column(name = "TRANSACTION_NAME")
    private String transactionName;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "LOCATION")
    private String location;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "DATETIME_TRANS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeTrans;
    @JoinColumn(name = "ACCOUNT_NUMBER", referencedColumnName = "ACCOUNT_NUMBER")
    @ManyToOne(optional = false)
    private DtAccounts accountNumber;
    @JoinColumn(name = "TRTYPE_ID", referencedColumnName = "TRTYPE_ID")
    @ManyToOne
    private DtTransactionTypes trtypeId;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private DtUsers idUser;

    public DtTransactions() {
    }

    public DtTransactions(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public DtTransactions(Integer transactionId, String transactionName, String description, String location, BigDecimal amount) {
        this.transactionId = transactionId;
        this.transactionName = transactionName;
        this.description = description;
        this.location = location;
        this.amount = amount;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDatetimeTrans() {
        return datetimeTrans;
    }

    public void setDatetimeTrans(Date datetimeTrans) {
        this.datetimeTrans = datetimeTrans;
    }

    public DtAccounts getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(DtAccounts accountNumber) {
        this.accountNumber = accountNumber;
    }

    public DtTransactionTypes getTrtypeId() {
        return trtypeId;
    }

    public void setTrtypeId(DtTransactionTypes trtypeId) {
        this.trtypeId = trtypeId;
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
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtTransactions)) {
            return false;
        }
        DtTransactions other = (DtTransactions) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DtTransactions[ transactionId=" + transactionId + " ]";
    }
    
}
