package ru.sgnhp.dao.impl;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import ru.sgnhp.dao.IAuthenticationDAO;
import ru.sgnhp.dto.UserLogin;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class AuthenticationDaoImpl implements IAuthenticationDAO {

    static String ATTRIBUTE_FOR_USER = "sAMAccountName";

    public Map authenticateUser(UserLogin login) {

        String returnedAtts[] = {"sAMAccountName", "sn", "givenName", "mail"};
        String searchFilter = "(&(objectClass=user)(" + ATTRIBUTE_FOR_USER + "=" + login.getLogin() + "))";
        //Create the search controls

        SearchControls searchCtls = new SearchControls();
        searchCtls.setReturningAttributes(returnedAtts);
        //Specify the search scope

        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String searchBase = login.getDn();
        Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        //Using starndard Port, check your instalation

        environment.put(Context.PROVIDER_URL, "ldap://" + login.getHost() + ":389");
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");

        environment.put(Context.SECURITY_PRINCIPAL, login.getLogin() + "@" + login.getDomain());
        environment.put(Context.SECURITY_CREDENTIALS, login.getPassword());
        LdapContext ctxGC = null;
        try {
            ctxGC = new InitialLdapContext(environment, null);
            //    Search for objects in the GC using the filter

            NamingEnumeration answer = ctxGC.search(searchBase, searchFilter, searchCtls);
            while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                Attributes attrs = sr.getAttributes();
                Map amap = null;
                if (attrs != null) {
                    amap = new HashMap();
                    NamingEnumeration ne = attrs.getAll();
                    while (ne.hasMore()) {
                        Attribute attr = (Attribute) ne.next();
                        amap.put(attr.getID(), attr.get());
                    }
                    ne.close();
                }
                return amap;
            }

        } catch (NamingException e) {
            System.out.println("Just reporting error");
            e.printStackTrace();
        }
        return null;
    }
}
