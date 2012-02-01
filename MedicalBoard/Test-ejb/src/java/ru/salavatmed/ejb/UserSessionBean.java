/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.salavatmed.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ru.salavatmed.entity.User;

/**
 *
 * @author 77han
 */
@Stateless
@LocalBean
public class UserSessionBean {
    @PersistenceContext(unitName = "perisitenceUnit")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<User> getUserByUsername(String username) {
        Query query = em.createNamedQuery("User.findByUsername").setParameter("username", username);
        return query.getResultList();
    }
    
}
