/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import ejb.DtTransactionTypesFacadeLocal;
import entities.DtTransactionTypes;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Juan David
 */
@Named(value = "transTypesMBean")
@RequestScoped
public class TransTypesMBean implements Serializable {

    @EJB
    private DtTransactionTypesFacadeLocal dtTransactionTypesFacade;
    private List<DtTransactionTypes> ListTransactionsTypes;
    private DtTransactionTypes ObjTransType = new DtTransactionTypes();

    public TransTypesMBean() {
    }

    public List<DtTransactionTypes> getListTransactionsTypes() {
        return ListTransactionsTypes;
    }

    public void setListTransactionsTypes(List<DtTransactionTypes> ListTransactionsTypes) {
        this.ListTransactionsTypes = ListTransactionsTypes;
    }

    public DtTransactionTypes getObjTransType() {
        return ObjTransType;
    }

    public void setObjTransType(DtTransactionTypes ObjTransType) {
        this.ObjTransType = ObjTransType;
    }
    
    @PostConstruct
    public void init() {
        ListTransactionsTypes = this.dtTransactionTypesFacade.findAll();
    }
    
        public String addTransType() {
        this.dtTransactionTypesFacade.create(ObjTransType);
        init();
        return "ManageTransTypes";
    }
        
    public String editTransType(DtTransactionTypes Tt) {
        this.ObjTransType = Tt;
        return "EditTransTypes";
    }

    public String editTransType() {
        this.dtTransactionTypesFacade.edit(ObjTransType);
        init();
        return "ManageTransTypes";
    }
    
        public String deleteTypeFromDB(DtTransactionTypes Tt) throws Exception {
        //Number of Types deleted
        int UsersDel = 0;
        try {
            this.ObjTransType = Tt;
            UsersDel = this.dtTransactionTypesFacade.DeleteByType(ObjTransType.getTrtypeId());
            String CustomMessage = " The user was deleted as well as trasactions and accounts associated.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Confirmation", CustomMessage));
        } catch (ELException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Transaction Error!"));
        }
        return "ManageTransTypes";
    }
}
