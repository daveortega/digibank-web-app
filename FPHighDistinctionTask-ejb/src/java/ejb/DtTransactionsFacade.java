/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtTransactions;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan David
 */
@Stateless
public class DtTransactionsFacade extends AbstractFacade<DtTransactions> implements DtTransactionsFacadeLocal {

    @PersistenceContext(unitName = "FPHighDistinctionTaskWeb-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DtTransactionsFacade() {
        super(DtTransactions.class);
    }

    @Override
    public List<DtTransactions> searchTransactionByID(int Id) throws Exception {
        List<DtTransactions> ResultList = new ArrayList<>();
        try {
            String jpql = "FROM DtTransactions n WHERE n.transactionId = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, Id);

            ResultList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultList;
    }

    @Override
    public List<DtTransactions> searchTransactionByName(String Name) throws Exception {
        List<DtTransactions> ResultList = new ArrayList<>();
        try {
            String jpql = "FROM DtTransactions n WHERE n.transactionName = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, Name);

            ResultList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultList;
    }

    @Override
    public List<DtTransactions> searchTransactionByIDandName(int Id, String Name) throws Exception {
        List<DtTransactions> ResultList = new ArrayList<>();
        try {
            String jpql = "FROM DtTransactions n WHERE n.transactionId = ?1 and n.transactionName = ?2";
            Query query = em.createQuery(jpql);
            query.setParameter(1, Id);
            query.setParameter(2, Name);

            ResultList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultList;
    }

    @Override
    public List<DtTransactions> searchTransactionByType(short Type) throws Exception {
        List<DtTransactions> ResultList = new ArrayList<>();
        try {
            String jpql = "FROM DtTransactions n WHERE n.trtypeId.trtypeId = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, Type);

            ResultList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultList;
    }

    @Override
    public List<DtTransactions> searchTransactionByIdandType(int Id, int Type) throws Exception {
        List<DtTransactions> ResultList = new ArrayList<>();
        try {
            String jpql = "FROM DtTransactions n WHERE n.transactionId = ?1 and n.trtypeId.trtypeId = ?2";
            Query query = em.createQuery(jpql);
            query.setParameter(1, Id);
            query.setParameter(2, Type);

            ResultList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultList;
    }

    @Override
    public List<DtTransactions> searchTransactionByNameandType(String Name, int Type) throws Exception {
        List<DtTransactions> ResultList = new ArrayList<>();
        try {
            String jpql = "FROM DtTransactions n WHERE n.transactionName = ?1 and n.trtypeId.trtypeId = ?2";
            Query query = em.createQuery(jpql);
            query.setParameter(1, Name);
            query.setParameter(2, Type);

            ResultList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultList;
    }

    @Override
    public List<DtTransactions> searchTransactionByIdandNameandType(int Id, String Name, short Type) throws Exception {
        List<DtTransactions> ResultList = new ArrayList<>();
        try {
            String jpql = "FROM DtTransactions n WHERE n.transactionId = ?1 and n.transactionName = ?2 and n.trtypeId.trtypeId = ?3";
            Query query = em.createQuery(jpql);
            query.setParameter(1, Id);
            query.setParameter(2, Name);
            query.setParameter(3, Type);

            ResultList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultList;
    }

    @Override
    public int DeleteByUser(String s) throws Exception {
        int rows = 0;
        try {
            String jpql = "delete FROM DtTransactions n WHERE n.idUser.idUser = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, s);

            rows = query.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
        return rows;
    }

}
