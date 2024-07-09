/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtUsers;
import java.util.List;
import javax.ejb.Local;
import Objects.PersonFilter;

/**
 *
 * @author Juan David
 */
@Local
public interface DtUsersFacadeLocal {

    void create(DtUsers dtUsers);

    void edit(DtUsers dtUsers);

    void remove(DtUsers dtUsers);

    DtUsers find(Object id);

    List<DtUsers> findAll();

    List<DtUsers> findRange(int[] range);

    int count();
    
    public DtUsers searchUser(String Name) throws Exception;
    
    int DeleteByUser(String s) throws Exception;
    
    List<DtUsers> FilterSelecction(List<PersonFilter> L) throws Exception;
    
    List<Object> CRMUsers();
    
}
