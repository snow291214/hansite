package ru.sgnhp.security;

import javax.naming.ldap.InitialLdapContext;
import org.apache.log4j.Logger;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.Authentication;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.providers.ldap.LdapAuthenticator;
import org.springframework.security.ui.WebAuthenticationDetails;

/**
 * Custom Spring Security LDAP authenticator which tries to bind to an LDAP server using the
 * passed-in credentials; does <strong>not</strong> require "master" credentials for an
 * initial bind prior to searching for the passed-in username.
 *
 * @author Jason
 */
public class LdapAuthenticatorImpl implements LdapAuthenticator {

    private SgnhpSpringSecurityContextSource contextFactory;
    private SgnhpSpringSecurityContextSource contextFactoryFirst;
    private SgnhpSpringSecurityContextSource contextFactorySecond;
    private SgnhpSpringSecurityContextSource contextFactoryThird;
    private String principalPrefix = "";

    public DirContextOperations authenticate(Authentication authentication) {

        // Grab the username and password out of the authentication object.
        String remoteIpAddress = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
//        Logger logger = Logger.getLogger(this.getClass().getName());

        contextFactory = contextFactoryFirst;
        principalPrefix = contextFactoryFirst.getPrincipalPrefix();

        if ((remoteIpAddress.startsWith(contextFactoryFirst.getNetworkPattern()))
                || (remoteIpAddress.startsWith("0:"))) {
            contextFactory = contextFactoryFirst;
            principalPrefix = contextFactoryFirst.getPrincipalPrefix();
        }
        if (remoteIpAddress.startsWith(contextFactorySecond.getNetworkPattern())) {
            contextFactory = contextFactorySecond;
            principalPrefix = contextFactorySecond.getPrincipalPrefix();
        }
        if (remoteIpAddress.startsWith(contextFactoryThird.getNetworkPattern())) {
            contextFactory = contextFactoryThird;
            principalPrefix = contextFactoryThird.getPrincipalPrefix();
        }
//        if (contextFactory == null) {
//            contextFactory = contextFactoryFirst;
//            principalPrefix = contextFactoryFirst.getPrincipalPrefix();
//        }
        String principal = principalPrefix + authentication.getName();
        //logger.info(String.format("User is authenticating. Username: %1$s Remote address: %2$s", principal, remoteIpAddress));
        String password = "";
        if (authentication.getCredentials() != null) {
            password = authentication.getCredentials().toString();
        }

        // If we have a valid username and password, try to authenticate.
        if (!("".equals(principal.trim())) && !("".equals(password.trim()))) {
            InitialLdapContext ldapContext = (InitialLdapContext) contextFactory.getReadWriteContext(principal, password);

            // We need to pass the context back out, so that the auth provider can add it to the
            // Authentication object.
            DirContextOperations authAdapter = new DirContextAdapter();
            authAdapter.addAttributeValue("ldapContext", ldapContext);

            return authAdapter;
        } else {
            throw new BadCredentialsException("Blank username and/or password!");
        }
    }

    /**
     * Since the InitialLdapContext that's stored as a property of an LdapAuthenticationToken is
     * transient (because it isn't Serializable), we need some way to recreate the
     * InitialLdapContext if it's null (e.g., if the LdapAuthenticationToken has been serialized
     * and deserialized). This is that mechanism.
     *
     * @param authenticator
     *          the LdapAuthenticator instance from your application's context
     * @param auth
     *          the LdapAuthenticationToken in which to recreate the InitialLdapContext
     * @return
     */
    static public InitialLdapContext recreateLdapContext(LdapAuthenticator authenticator,
            LdapAuthenticationToken auth) {
        DirContextOperations authAdapter = authenticator.authenticate(auth);
        InitialLdapContext context = (InitialLdapContext) authAdapter.getObjectAttribute("ldapContext");
        auth.setContext(context);
        return context;
    }

//    public DefaultSpringSecurityContextSource getContextFactory() {
//        return contextFactory;
//    }
    /**
     * Set the context factory to use for generating a new LDAP context.
     *
     * @param contextFactory
     */
    public void setContextFactory(SgnhpSpringSecurityContextSource contextFactory) {
        this.contextFactory = contextFactory;
    }

    public String getPrincipalPrefix() {
        return principalPrefix;
    }

    /**
     * Set the string to be prepended to all principal names prior to attempting authentication
     * against the LDAP server.  (For example, if the Active Directory wants the domain-name-plus
     * backslash prepended, use this.)
     *
     * @param principalPrefix
     */
    public void setPrincipalPrefix(String principalPrefix) {
        if (principalPrefix != null) {
            this.principalPrefix = principalPrefix;
        } else {
            this.principalPrefix = "";
        }
    }

    public void setContextFactoryFirst(SgnhpSpringSecurityContextSource contextFactoryFirst) {
        this.contextFactoryFirst = contextFactoryFirst;
    }

    public void setContextFactorySecond(SgnhpSpringSecurityContextSource contextFactorySecond) {
        this.contextFactorySecond = contextFactorySecond;
    }

    public void setContextFactoryThird(SgnhpSpringSecurityContextSource contextFactoryThird) {
        this.contextFactoryThird = contextFactoryThird;
    }
}
