/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtUsers;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Juan David
 */
@Remote
public interface DtUsersFacadeRemote {

    void create(DtUsers dtUsers);

    void edit(DtUsers dtUsers);

    void remove(DtUsers dtUsers);

    DtUsers find(Object id);

    List<DtUsers> findAll();

    List<DtUsers> findRange(int[] range);

    int count();
    
}
