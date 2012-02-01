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
import ru.salavatmed.entity.Hazard;

/**
 *
 * @author 77han
 */
@Stateless
@LocalBean
public class HazardSessionBean {

    @PersistenceContext(unitName = "perisitenceUnit")
    private EntityManager em;

    public void persist(Hazard object) {
        em.persist(object);
    }

    public List<Hazard> getHazardByAppendix(String appendix) {
        if (appendix == null) {
            return null;
        }
        Query query = em.createNamedQuery("Hazard.findByAppendix").setParameter("appendix", Integer.parseInt(appendix));
        return query.getResultList();
    }

    public Hazard getHazardByAppendixAndParagraph(String appendix, String paragraph) {
        if (paragraph == null) {
            return null;
        }
        Query query = em.createNamedQuery("Hazard.findByAppendixAndParagraph")
                .setParameter("appendix", Integer.parseInt(appendix))
                .setParameter("paragraph", paragraph);
        return (Hazard) query.getSingleResult();
    }

    public Hazard getNameByParagraph(String paragraph) {
        if (paragraph == null) {
            return null;
        }
        Query query = em.createNamedQuery("Hazard.findByParagraph").setParameter("paragraph", paragraph);
        return (Hazard) query.getSingleResult();
    }
//    public Hazard getNameByAppendixAndParagraph(String paragraph, String appendix) {
//        if (paragraph == null) {
//            return null;
//        }
//        Query query = em.createNamedQuery("Hazard.findByAppendixAndParagraph")
//                .setParameter("appendix", Integer.parseInt(appendix))
//                .setParameter("paragraph", paragraph);
//        return (Hazard)query.getSingleResult();
//    }
}
