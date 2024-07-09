/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "DT_TRANSACTION_TYPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtTransactionTypes.findAll", query = "SELECT d FROM DtTransactionTypes d")
    , @NamedQuery(name = "DtTransactionTypes.findByTrtypeId", query = "SELECT d FROM DtTransactionTypes d WHERE d.trtypeId = :trtypeId")
    , @NamedQuery(name = "DtTransactionTypes.findByTypeName", query = "SELECT d FROM DtTransactionTypes d WHERE d.typeName = :typeName")
    , @NamedQuery(name = "DtTransactionTypes.findByMovementTypeId", query = "SELECT d FROM DtTransactionTypes d WHERE d.movementTypeId = :movementTypeId")})
public class DtTransactionTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRTYPE_ID")
    private Short trtypeId;
    @Basic(optional = false)
    @Column(name = "TYPE_NAME")
    private String typeName;
    @Basic(optional = false)
    @Column(name = "MOVEMENT_TYPE_ID")
    private String movementTypeId;
    @OneToMany(mappedBy = "trtypeId")
    private List<DtTransactions> dtTransactionsList;

    public DtTransactionTypes() {
    }

    public DtTransactionTypes(Short trtypeId) {
        this.trtypeId = trtypeId;
    }

    public DtTransactionTypes(Short trtypeId, String typeName, String movementTypeId) {
        this.trtypeId = trtypeId;
        this.typeName = typeName;
        this.movementTypeId = movementTypeId;
    }

    public Short getTrtypeId() {
        return trtypeId;
    }

    public void setTrtypeId(Short trtypeId) {
        this.trtypeId = trtypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMovementTypeId() {
        return movementTypeId;
    }

    public void setMovementTypeId(String movementTypeId) {
        this.movementTypeId = movementTypeId;
    }

    @XmlTransient
    public List<DtTransactions> getDtTransactionsList() {
        return dtTransactionsList;
    }

    public void setDtTransactionsList(List<DtTransactions> dtTransactionsList) {
        this.dtTransactionsList = dtTransactionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trtypeId != null ? trtypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtTransactionTypes)) {
            return false;
        }
        DtTransactionTypes other = (DtTransactionTypes) object;
        if ((this.trtypeId == null && other.trtypeId != null) || (this.trtypeId != null && !this.trtypeId.equals(other.trtypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return trtypeId+". "+typeName;
    }
    
}
