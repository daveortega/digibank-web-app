/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtTransactionTypes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan David
 */
@Local
public interface DtTransactionTypesFacadeLocal {

    void create(DtTransactionTypes dtTransactionTypes);

    void edit(DtTransactionTypes dtTransactionTypes);

    void remove(DtTransactionTypes dtTransactionTypes);

    DtTransactionTypes find(Object id);

    List<DtTransactionTypes> findAll();

    List<DtTransactionTypes> findRange(int[] range);

    int count();
    
    List<DtTransactionTypes> findWandD();
    
    int DeleteByType(Short s) throws Exception;
    
    DtTransactionTypes findByID(short s);
    
}
