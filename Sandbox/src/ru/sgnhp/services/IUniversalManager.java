/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.services;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 48han
 */
public interface IUniversalManager {
    /**
     * Generic method used to get a all objects of a particular type.
     * @param clazz the type of objects
     * @return List of populated objects
     */
    List getAll(Class clazz);

    /**
     * Generic method to get an object based on class and identifier.
     *
     * @param clazz model class to lookup
     * @param id the identifier (primary key) of the class
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    Object get(Class clazz, Serializable id);

    /**
     * Generic method to save an object.
     * @param o the object to save
     * @return a populated object
     */
    Object save(Object o);

    /**
     * Generic method to delete an object based on class and id
     * @param clazz model class to lookup
     * @param id the identifier of the class
     */
    void remove(Class clazz, Serializable id);
}
