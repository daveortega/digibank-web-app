package ManagedBeans;

import ejb.DtUsersFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import entities.DtUsers;
import Objects.PersonFilter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Juan David
 */
@Named(value = "usersMBean")
@RequestScoped
public class UsersMBean implements Serializable {

    @EJB
    private DtUsersFacadeLocal dtUsersFacade;

    private List<DtUsers> ListUsers;
    private DtUsers ObjUser = new DtUsers();
    private String UserID;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;

    public List<DtUsers> getListUsers() {
        return ListUsers;
    }

    public void setListUsers(List<DtUsers> ListUsers) {
        this.ListUsers = ListUsers;
    }

    public DtUsers getObjUser() {
        return ObjUser;
    }

    public void setObjUser(DtUsers ObjUser) {
        this.ObjUser = ObjUser;
    }

    public UsersMBean() {
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @PostConstruct
    public void init() {
        ListUsers = this.dtUsersFacade.findAll();
    }

    public String AddUser() {
        //set the password with a SHA-256 encrypted value
        //retrieved from: https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha256-in-java
        ObjUser.setPassword(getSHA256(ObjUser.getPassword()));
        this.dtUsersFacade.create(ObjUser);
        init();
        return "ManageUsers";
    }

    public String EditUser(DtUsers Tt) {
        this.ObjUser = Tt;
        return "EditUsers";
    }

    public String EditUser() {
        ObjUser.setPassword(getSHA256(ObjUser.getPassword()));
        this.dtUsersFacade.edit(ObjUser);
        init();
        return "ManageUsers";
    }

    //Function to retrieve from the database if the users exist.
    public void validateUser(FacesContext f, UIComponent c, Object obj) throws Exception {
        String s = (String) obj;
        DtUsers look = new DtUsers();
        look = this.dtUsersFacade.searchUser(s);
        if (look != null) {
            System.out.print("Error");
            throw new ValidatorException(new FacesMessage("User id already exists."));
        }
    }

    public String ViewDetailUsers(DtUsers Tt) {
        this.ObjUser = Tt;
        return "UserDetails";
    }

    public String DeleteUserFromDB(DtUsers Tt) throws Exception {
        //Number of Users deleted
        int UsersDel = 0;
        try {
            this.ObjUser = Tt;
            UsersDel = this.dtUsersFacade.DeleteByUser(ObjUser.getIdUser());
            String CustomMessage = " The user was deleted as well as trasactions and accounts associated.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Confirmation", CustomMessage));
        } catch (ELException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Transaction Error!"));
        }
        return "ManageUsers";
    }

//    public void searchByCriteria() throws Exception {
//        String jpql;
//        jpql = "FROM DtUsers n WHERE";
//        if (UserID.isEmpty()) {
//             jpql = jpql + " 1=1";
//        } else {
//            jpql = jpql + " n.idUser LIKE CONCAT('%', ?1, '%')";
//        }
//
//        if (firstName.isEmpty()) {
//            jpql = jpql + " and 1=1";
//        } else {
//            jpql = jpql + " and n.firstName LIKE CONCAT('%', ?2, '%')";
//        }
//
//        if (lastName.isEmpty()) {
//            jpql = jpql + " and 1=1";
//        } else {
//            jpql = jpql + " and n.lastName LIKE CONCAT('%', ?3, '%')";
//        }
//
//        if (email.isEmpty()) {
//            jpql = jpql + " and 1=1";
//        } else {
//            jpql = jpql + " and n.email LIKE CONCAT('%', ?4, '%')";
//        }
//
//        if (userType == null) {
//            jpql = jpql + " and 1=1";
//        } else {
//            jpql = jpql + " and n.userType LIKE CONCAT('%', ?5, '%')";
//        }
//
//        if (UserID.isEmpty() && UserID.isEmpty() && lastName.isEmpty() && email.isEmpty() && userType.isEmpty()) {
//            init();
//        } else {
//            ListUsers = this.dtUsersFacade.FilterSelecction(jpql, UserID, firstName);
//        }
//    }
    public void searchByCriteria() throws Exception {
        //Create an object with two string and an array to contain all the values
        List<PersonFilter> SearchArray = new ArrayList<>();
        PersonFilter InsertPerson = new PersonFilter();
        if (!UserID.isEmpty()) {
            InsertPerson.setFieldName("idUser");
            InsertPerson.setFieldvalue(UserID);
            SearchArray.add(InsertPerson);
            InsertPerson = new PersonFilter();
        }
        if (!firstName.isEmpty()) {
            InsertPerson.setFieldName("firstName");
            InsertPerson.setFieldvalue(firstName);
            SearchArray.add(InsertPerson);
            InsertPerson = new PersonFilter();
        }
        if (!lastName.isEmpty()) {
            InsertPerson.setFieldName("lastName");
            InsertPerson.setFieldvalue(lastName);
            SearchArray.add(InsertPerson);
            InsertPerson = new PersonFilter();
        }
        if (!email.isEmpty()) {
            InsertPerson.setFieldName("email");
            InsertPerson.setFieldvalue(email);
            SearchArray.add(InsertPerson);
            InsertPerson = new PersonFilter();
        }
        if (userType != null) {
            InsertPerson.setFieldName("userType");
            InsertPerson.setFieldvalue(userType);
            SearchArray.add(InsertPerson);
            InsertPerson = new PersonFilter();
        }
        if (UserID.isEmpty() && firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && userType == null) {
            init();
        } else {
            ListUsers = this.dtUsersFacade.FilterSelecction(SearchArray);
        }
    }

    public static String getSHA256(String data) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
            byte byteData[] = md.digest();

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return sb.toString();
    }

}
