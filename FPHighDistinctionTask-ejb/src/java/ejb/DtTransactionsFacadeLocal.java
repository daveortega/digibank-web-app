/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtTransactions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan David
 */
@Local
public interface DtTransactionsFacadeLocal {

    void create(DtTransactions dtTransactions);

    void edit(DtTransactions dtTransactions);

    void remove(DtTransactions dtTransactions);

    DtTransactions find(Object id);

    List<DtTransactions> findAll();

    List<DtTransactions> findRange(int[] range);

    int count();

    public List<DtTransactions> searchTransactionByID(int Id) throws Exception;

    public List<DtTransactions> searchTransactionByName(String Name) throws Exception;

    public List<DtTransactions> searchTransactionByIDandName(int Id, String Name) throws Exception;

    public List<DtTransactions> searchTransactionByType(short Type) throws Exception;

    public List<DtTransactions> searchTransactionByIdandType(int Id, int Type) throws Exception;

    public List<DtTransactions> searchTransactionByNameandType(String Name, int Type) throws Exception;

    public List<DtTransactions> searchTransactionByIdandNameandType(int Id, String Name, short Type) throws Exception;

    int DeleteByUser(String s) throws Exception;

}
