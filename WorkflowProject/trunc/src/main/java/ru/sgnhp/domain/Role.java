package ru.sgnhp.domain;

import java.io.Serializable;
import org.springframework.security.GrantedAuthority;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class Role implements Serializable, GrantedAuthority{

    public Role(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public int compareTo(Object o) {
        return (equals(o) ? 0 : -1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
