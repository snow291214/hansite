package ru.sgnhp.security;

import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.InitialLdapContext;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.ldap.LdapAuthenticator;
import ru.sgnhp.service.IUserManagerService;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
public class LdapAuthenticationProvider implements AuthenticationProvider {

    private LdapAuthenticator authenticator;
    private IUserManagerService userManagerService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Authenticate, using the passed-in credentials.
        DirContextOperations authAdapter = authenticator.authenticate(authentication);

        // Creating an LdapAuthenticationToken (rather than using the existing Authentication
        // object) allows us to add the already-created LDAP context for our app to use later.
        //final String currentUser = authentication.getName();
        String userRole = "ROLE_USER";
        if (userManagerService.getUserByLogin(authentication.getName()) != null) {
            userRole = userManagerService.getUserByLogin(authentication.getName()).getUserGroupBean().getName();
        }
        //LdapAuthenticationToken ldapAuth = new LdapAuthenticationToken(authentication, "ROLE_USER");
        LdapAuthenticationToken ldapAuth = new LdapAuthenticationToken(authentication, userRole);
        GrantedAuthority authority = new GrantedAuthorityImpl("USER_ROLE");
        ldapAuth.addAuthority(authority);
        InitialLdapContext ldapContext = (InitialLdapContext) authAdapter.getObjectAttribute("ldapContext");
        if (ldapContext != null) {
            ldapAuth.setContext(ldapContext);
        }

        return ldapAuth;
    }

    public boolean supports(Class clazz) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz));
    }

    public LdapAuthenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(LdapAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    public void setUserManagerService(IUserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
