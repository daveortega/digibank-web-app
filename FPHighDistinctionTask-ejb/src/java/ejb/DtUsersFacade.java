/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import Objects.PersonFilter;
import entities.DtUsers;
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
public class DtUsersFacade extends AbstractFacade<DtUsers> implements DtUsersFacadeLocal {

    @PersistenceContext(unitName = "FPHighDistinctionTaskWeb-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DtUsersFacade() {
        super(DtUsers.class);
    }

    @Override
    public DtUsers searchUser(String Name) throws Exception {
        DtUsers LookUser = em.find(DtUsers.class, Name);
        //Person.getTags().size();
        return LookUser;
    }

    @Override
    public int DeleteByUser(String s) throws Exception {
        int rows = 0;
        try {
            String jpql = "delete FROM DtUsers n WHERE n.idUser = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, s);

            rows = query.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
        return rows;
    }

    @Override
    public List<DtUsers> FilterSelecction(List<PersonFilter> L) throws Exception {
        List<DtUsers> ResultList = new ArrayList<>();
        boolean FTime = true;
        String IniJPQL = "FROM DtUsers n WHERE";

        //go to the array and create the SQL according to the parameters received.
        for (int x = 0; x < L.size(); x++) {
            String RunJPQL = "";
            if (!L.get(x).getFieldName().isEmpty()) {
                if (FTime) {
                    FTime = false;
                } else {
                    RunJPQL = RunJPQL + " AND";
                }
                int NoParam = x + 1;
                RunJPQL = RunJPQL + " n." + L.get(x).getFieldName() + " LIKE CONCAT('%', ?" + NoParam + ", '%')";
                IniJPQL = IniJPQL + RunJPQL;
                System.out.println(RunJPQL);
                System.out.println(IniJPQL);
            }
        }

        //insert the parameters from the array.
        try {
            Query query = em.createQuery(IniJPQL);

            for (int x = 0; x < L.size(); x++) {
                int NoParam = x + 1;
                query.setParameter(NoParam, L.get(x).getFieldvalue());
            }
            ResultList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return ResultList;
    }

    @Override
    public List<Object> CRMUsers() {
        List<Object> resulList = new ArrayList<>();
        try {
            String jpql = "SELECT n.firstName, n.lastName, n.phoneNumber, n.address, n.email FROM DtUsers n";
            Query query = em.createQuery(jpql);

            resulList = query.getResultList();

        } catch (Exception e) {
            throw e;
        }
        return resulList;
    }

}
