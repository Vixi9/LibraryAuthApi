package fr.ensicaen.ensibrary.libraryauthapi.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<Users> users;

    @ManyToMany
    @JoinTable(
            name = "ROLE_PRIVILEGES",
            joinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "PRIVILEGE_ID", referencedColumnName = "ID"))
    private Collection<Privilege> privileges;
}
