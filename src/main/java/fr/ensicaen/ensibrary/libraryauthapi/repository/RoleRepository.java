package fr.ensicaen.ensibrary.libraryauthapi.repository;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("from Role where name = 'ROLE_ADMIN'")
    Role getAdminRole();

    @Query("from Role where name = 'ROLE_LIBRARIAN'")
    Role getLibrarianRole();

    @Query("from Role where name = 'ROLE_AGENT'")
    Role getAgentRole();

    @Query("from Role where name = 'ROLE_USER'")
    Role getUserRole();
}
