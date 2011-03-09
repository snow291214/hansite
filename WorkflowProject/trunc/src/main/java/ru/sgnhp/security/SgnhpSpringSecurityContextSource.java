package ru.sgnhp.security;

import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class SgnhpSpringSecurityContextSource extends DefaultSpringSecurityContextSource {

    private String principalPrefix = "";
    private String networkPattern = "";

    public SgnhpSpringSecurityContextSource(String providerUrl) {
        super(providerUrl);
    }

    public String getPrincipalPrefix() {
        return principalPrefix;
    }

    public void setPrincipalPrefix(String principalPrefix) {
        this.principalPrefix = principalPrefix;
    }

    public String getNetworkPattern() {
        return networkPattern;
    }

    public void setNetworkPattern(String networkPattern) {
        this.networkPattern = networkPattern;
    }
}