package fr.ensicaen.ensibrary.libraryauthapi.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(
                    name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "ID"))
    private Collection<Role> roles;
}
