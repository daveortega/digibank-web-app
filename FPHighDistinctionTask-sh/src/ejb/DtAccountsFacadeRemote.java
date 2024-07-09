/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtAccounts;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Juan David
 */
@Remote
public interface DtAccountsFacadeRemote {

    void create(DtAccounts dtAccounts);

    void edit(DtAccounts dtAccounts);

    void remove(DtAccounts dtAccounts);

    DtAccounts find(Object id);

    List<DtAccounts> findAll();

    List<DtAccounts> findRange(int[] range);

    int count();
    
    List<DtAccounts> FindByUserID(String s);

}
