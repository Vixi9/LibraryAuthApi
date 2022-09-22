package fr.ensicaen.ensibrary.libraryauthapi.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private Long id;

    @Column()
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
