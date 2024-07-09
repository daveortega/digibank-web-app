/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServiceBeans;

import ejb.DtUsersFacadeLocal;
import entities.DtUsers;
import java.io.StringWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXB;

/**
 *
 * @author Juan David
 */
@Stateless
@Path("DBusers")
public class UserWSBean {

    @EJB
    private DtUsersFacadeLocal dtUsersFacade;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DtUsers> findAll() {
        List<DtUsers> CRMUsers = this.dtUsersFacade.findAll();
    
//        for (int x = 0; x < CRMUsers.size(); x++) {
//             CRMUsers.get(x).setPassword("");
//             CRMUsers.get(x).setUserType("");
//        }
        return CRMUsers;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DtUsers findbyID(@PathParam("id") String id) throws Exception {
        return this.dtUsersFacade.searchUser(id);
    }
}
