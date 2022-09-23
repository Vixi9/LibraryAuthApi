package fr.ensicaen.ensibrary.libraryauthapi.repository;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
