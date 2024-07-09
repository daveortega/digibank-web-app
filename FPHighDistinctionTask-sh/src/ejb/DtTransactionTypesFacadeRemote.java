/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtTransactionTypes;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Juan David
 */
@Remote
public interface DtTransactionTypesFacadeRemote {

    void create(DtTransactionTypes dtTransactionTypes);

    void edit(DtTransactionTypes dtTransactionTypes);

    void remove(DtTransactionTypes dtTransactionTypes);

    DtTransactionTypes find(Object id);

    List<DtTransactionTypes> findAll();

    List<DtTransactionTypes> findRange(int[] range);

    int count();
    
    List<DtTransactionTypes> findWandD();
    
    DtTransactionTypes findByID(short s);
    
}
