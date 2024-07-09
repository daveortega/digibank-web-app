/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import ejb.DtAccountsFacadeLocal;
import ejb.DtUsersFacadeLocal;
import entities.DtUsers;
import entities.DtAccounts;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Juan David
 */
@Named(value = "userLogMBean")
@SessionScoped
public class UserLogMBean implements Serializable {

    @EJB
    private DtUsersFacadeLocal dtUsersFacade;

    @EJB
    private DtAccountsFacadeLocal dtAccountsFacade;
    
    private DtUsers LogUser = new DtUsers();
    private List<DtAccounts> LogUserAccounts;
    
    public DtUsers getLogUser() {
        return LogUser;
    }

    public void setLogUser(DtUsers LogUser) {
        this.LogUser = LogUser;
    }

    public List<DtAccounts> getLogUserAccounts() {
        return LogUserAccounts;
    }

    public void setLogUserAccounts(List<DtAccounts> LogUserAccounts) {
        this.LogUserAccounts = LogUserAccounts;
    }

    public UserLogMBean() {
    }

    @PostConstruct
    public void init() {
        try {
            //It brings the currect logged user in the application
            String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
            setLogUser(FindUSerByID(username));
            setLogUserAccounts(dtAccountsFacade.FindByUserID(username));
            System.out.println("Llegue aca");
            System.out.println(getLogUser().getUserType());
        } catch (Exception ex) {
            Logger.getLogger(UsersMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DtUsers FindUSerByID(String UserID) throws Exception {
        DtUsers look = new DtUsers();
        look = this.dtUsersFacade.searchUser(UserID);
        return look;
    }
    
   public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redrect=true";
    }
}
