/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import ejb.DtTransactionTypesFacadeLocal;
import ejb.DtTransactionsFacadeLocal;
import entities.DtTransactions;
import entities.DtTransactionTypes;
import entities.DtAccounts;
import entities.DtUsers;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import java.util.List;
import javax.el.ELException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Juan David
 */
@Named(value = "transactionMbean")
@RequestScoped
public class TransactionMbean implements Serializable {

    @EJB
    private DtTransactionsFacadeLocal dtTransactionsFacade;

    @EJB
    private DtTransactionTypesFacadeLocal dtTransactionTypesFacade;

    private List<DtTransactions> ListTransactions;
    private List<DtTransactionTypes> ListTransactionsTypes;
    private List<DtTransactionTypes> ListWandCTypes;
    private DtTransactions ObjTrans = new DtTransactions();
    private Integer TransNo;
    private String TransName;
    private short TransType;
    private DtAccounts ObjAddAcctNo = new DtAccounts();
    private DtTransactionTypes ObjAddTransType = new DtTransactionTypes();
    private DtUsers ObjAddUser = new DtUsers();

    public List<DtTransactions> getListTransactions() {
        return ListTransactions;
    }

    public void setListTransactions(List<DtTransactions> ListTransactions) {
        this.ListTransactions = ListTransactions;
    }

    public List<DtTransactionTypes> getListTransactionsTypes() {
        return ListTransactionsTypes;
    }

    public void setListTransactionsTypes(List<DtTransactionTypes> ListTransactionsTypes) {
        this.ListTransactionsTypes = ListTransactionsTypes;
    }

    public Integer getTransNo() {
        return TransNo;
    }

    public void setTransNo(Integer TransNo) {
        this.TransNo = TransNo;
    }

    public String getTransName() {
        return TransName;
    }

    public void setTransName(String TransName) {
        this.TransName = TransName;
    }

    public short getTransType() {
        return TransType;
    }

    public void setTransType(short TransType) {
        this.TransType = TransType;
    }

    public DtTransactions getObjTrans() {
        return ObjTrans;
    }

    public void setObjTrans(DtTransactions ObjTrans) {
        this.ObjTrans = ObjTrans;
    }

    public List<DtTransactionTypes> getListWandCTypes() {
        return ListWandCTypes;
    }

    public void setListWandCTypes(List<DtTransactionTypes> ListWandCTypes) {
        this.ListWandCTypes = ListWandCTypes;
    }

    public DtAccounts getObjAddAcctNo() {
        return ObjAddAcctNo;
    }

    public void setObjAddAcctNo(DtAccounts ObjAddAcctNo) {
        this.ObjAddAcctNo = ObjAddAcctNo;
    }

    public DtTransactionTypes getObjAddTransType() {
        return ObjAddTransType;
    }

    public void setObjAddTransType(DtTransactionTypes ObjAddTransType) {
        this.ObjAddTransType = ObjAddTransType;
    }

    public DtUsers getObjAddUser() {
        return ObjAddUser;
    }

    public void setObjAddUser(DtUsers ObjAddUser) {
        this.ObjAddUser = ObjAddUser;
    }

    public TransactionMbean() {
    }

    @PostConstruct
    public void init() {
        ListTransactions = this.dtTransactionsFacade.findAll();
        ListTransactionsTypes = this.dtTransactionTypesFacade.findAll();
        ListWandCTypes = this.dtTransactionTypesFacade.findWandD();
    }

    public void SearchByCriteria() throws Exception {
        //Creating a numerical expression, each variable will have a differente value 
        int VarTransNo = 0;
        int VarTransName = 0;
        int VarTransType = 0;
        int TransNoInt = 0;

        if (TransNo != null) {
            TransNoInt = Integer.parseInt(TransNo.toString());
        }
        //Aplying logic to obtain numbers
        if (TransNoInt > 0) {
            VarTransNo = 1;
        }
        if (TransName.length() > 0) {
            VarTransName = 2;
        }
        if (TransType > 0) {
            VarTransType = 4;
        }

        //sum all number in a variable
        int validator = VarTransNo + VarTransName + VarTransType;

        //Instancing the variable to receive the object list
        List<DtTransactions> ListOut = new ArrayList<>();

        //Choose the best path to retrieve data
        switch (validator) {
            case 0:
                System.out.print("Zero");
                ListTransactions = dtTransactionsFacade.findAll();
                break;
            case 1:
                System.out.print("one");
                ListTransactions = dtTransactionsFacade.searchTransactionByID(TransNoInt);
                break;
            case 2:
                System.out.print("two");
                ListTransactions = dtTransactionsFacade.searchTransactionByName(TransName);
                break;
            case 3:
                System.out.print("three");
                ListTransactions = dtTransactionsFacade.searchTransactionByIDandName(TransNoInt, TransName);
                break;
            case 4:
                System.out.print("four");
                ListTransactions = dtTransactionsFacade.searchTransactionByType(TransType);
                break;
            case 5:
                System.out.print("five");
                ListTransactions = dtTransactionsFacade.searchTransactionByIdandType(TransNoInt, TransType);
                break;
            case 6:
                System.out.print("six");
                ListTransactions = dtTransactionsFacade.searchTransactionByNameandType(TransName, TransType);
                break;
            case 7:
                System.out.print("seven");
                ListTransactions = dtTransactionsFacade.searchTransactionByIdandNameandType(TransNoInt, TransName, TransType);
                break;
        }
    }

    public String ViewDetailTransaction(DtTransactions Tt) {
        this.ObjTrans = Tt;
        return "DetailTransaction";
    }

    public void addTransaction() throws ParseException {
        try {
            //Inserting Account and Transaction Type objects
            ObjTrans.setAccountNumber(ObjAddAcctNo);
            ObjTrans.setTrtypeId(ObjAddTransType);

            //Getting and inserting user object
            FacesContext context = FacesContext.getCurrentInstance();
            Application application = context.getApplication();
            UserLogMBean LogUser = application.evaluateExpressionGet(context, "#{userLogMBean}", UserLogMBean.class);
            ObjTrans.setIdUser(LogUser.getLogUser());
            
            //Gettting current datetime
            Date CurrentTime = new Date();
            ObjTrans.setDatetimeTrans(CurrentTime);

            //persisting the information in the database
            dtTransactionsFacade.create(ObjTrans);

            // This section will be a balance manager
            
            //Creating a new instance
            ObjTrans = new DtTransactions();
            ObjAddAcctNo = new DtAccounts();
            ObjAddTransType = new DtTransactionTypes();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Transaction Succesful!"));
        } catch (ELException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Transaction Succesful!"));
        }
    }

}
