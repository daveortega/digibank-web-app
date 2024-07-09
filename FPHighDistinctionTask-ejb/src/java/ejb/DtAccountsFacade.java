/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.DtAccounts;
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
public class DtAccountsFacade extends AbstractFacade<DtAccounts> implements DtAccountsFacadeLocal {

    @PersistenceContext(unitName = "FPHighDistinctionTaskWeb-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DtAccountsFacade() {
        super(DtAccounts.class);
    }

    @Override
    public List<DtAccounts> FindByUserID(String s) {
        List<DtAccounts> ResultAccounts = new ArrayList<>();
           try {
            String jpql = "FROM DtAccounts n WHERE n.idUser.idUser = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, s);

            ResultAccounts = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultAccounts;
    }

    @Override
    public int DeleteByUser(String s) throws Exception {
        int rows = 0; 
        try {
            String jpql = "delete FROM DtAccounts n WHERE n.idUser.idUser = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, s);

            rows = query.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
        return rows;
    }
    
} 
