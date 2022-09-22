package fr.ensicaen.ensibrary.libraryauthapi.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private Long id;

    @Column()
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "ROLE_PRIVILEGES",
            joinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "PRIVILEGE_ID", referencedColumnName = "ID"))
    private Collection<Privilege> privileges;
}
