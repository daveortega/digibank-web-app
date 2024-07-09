/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtTransactionTypes;
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
public class DtTransactionTypesFacade extends AbstractFacade<DtTransactionTypes> implements DtTransactionTypesFacadeLocal {

    @PersistenceContext(unitName = "FPHighDistinctionTaskWeb-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DtTransactionTypesFacade() {
        super(DtTransactionTypes.class);
    }

    @Override
    public List<DtTransactionTypes> findWandD() {
       List<DtTransactionTypes> ResultWandC = new ArrayList<>();
       List<Short> InTypes = new ArrayList<>();
       InTypes.add(Short.parseShort("1"));
       InTypes.add(Short.parseShort("2"));
           try {
            String jpql = "FROM DtTransactionTypes t WHERE t.trtypeId IN ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, InTypes);

            ResultWandC = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultWandC;

    }

    @Override
    public int DeleteByType(Short s) throws Exception {
       int rows = 0;
        try {
            String jpql = "delete FROM DtTransactionTypes n WHERE n.trtypeId = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, s);

            rows = query.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
        return rows;  
    }
    
    @Override
    public DtTransactionTypes findByID(short s) {
        DtTransactionTypes Result = new DtTransactionTypes();
        try {
            String jpql = "FROM DtTransactionTypes t WHERE t.trtypeId = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, s);

            List<DtTransactionTypes> QueryOut = query.getResultList();
            Result = QueryOut.get(0);

        } catch (Exception e) {
            throw e;
        }
        return Result;

    }
    
}
