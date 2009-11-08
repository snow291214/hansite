package ru.sgnhp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****
 */
@Entity
@Table(name = "groups")
public class Groups implements Serializable {

    private static final long serialVersionUID = 382157955767771714L;
    @Id
    @Column(name = "Uid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;

    //private Set users = new HashSet();

    public Groups() {
    }

    public Groups(Long id, String name) {
        this.uid = id;
        this.name = name;
    }

    public Long getId() {
        return uid;
    }

    public void setId(Long id) {
        this.uid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
