package ru.sgnhp.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.ldap.InitialLdapContext;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.providers.AbstractAuthenticationToken;

/**
 * <p>
 * Authentication token to use when an app needs further access to the LDAP context used to
 * authenticate the user.
 * </p>
 *
 * <p>
 * When this is the Authentication object stored in the Spring Security context, an application
 * can retrieve the current LDAP context thusly:
 * </p>
 *
 * <pre>
 * LdapAuthenticationToken ldapAuth = (LdapAuthenticationToken) SecurityContextHolder
 *              .getContext().getAuthentication();
 * InitialLdapContext ldapContext = ldapAuth.getContext();
 * </pre>
 *
 * @author Jason
 *
 */
public class LdapAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -5040340622950665401L;
    private Authentication auth;
    transient private InitialLdapContext context;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    /**
     * Construct a new LdapAuthenticationToken, using an existing Authentication object and
     * granting all users a default authority.
     *
     * @param auth
     * @param defaultAuthority
     */
    public LdapAuthenticationToken(Authentication auth, GrantedAuthority defaultAuthority) {
        this.auth = auth;
        if (auth.getAuthorities() != null) {
            this.authorities.addAll(Arrays.asList(auth.getAuthorities()));
        }
        if (defaultAuthority != null) {
            this.authorities.add(defaultAuthority);
        }
        super.setAuthenticated(true);
    }

    /**
     * Construct a new LdapAuthenticationToken, using an existing Authentication object and
     * granting all users a default authority.
     *
     * @param auth
     * @param defaultAuthority
     */
    public LdapAuthenticationToken(Authentication auth, String defaultAuthority) {
        this(auth, new GrantedAuthorityImpl(defaultAuthority));
    }

    @Override
    public GrantedAuthority[] getAuthorities() {
        GrantedAuthority[] authoritiesArray = this.authorities.toArray(new GrantedAuthority[0]);
        return authoritiesArray;
    }

    public void addAuthority(GrantedAuthority authority) {
        this.authorities.add(authority);
    }

    public Object getCredentials() {
        return auth.getCredentials();
    }

    public Object getPrincipal() {
        return auth.getPrincipal();
    }

    /**
     * Retrieve the LDAP context attached to this user's authentication object.
     *
     * @return the LDAP context
     */
    public InitialLdapContext getContext() {
        return context;
    }

    /**
     * Attach an LDAP context to this user's authentication object.
     *
     * @param context
     *          the LDAP context
     */
    public void setContext(InitialLdapContext context) {
        this.context = context;
    }
}