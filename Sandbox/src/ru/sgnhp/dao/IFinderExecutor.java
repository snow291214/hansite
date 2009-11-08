/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.sgnhp.dao;

import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author alexey
 */
public interface IFinderExecutor {
  @SuppressWarnings("unchecked")
  List executeFinder(Method m, Object[] arguments);
}

