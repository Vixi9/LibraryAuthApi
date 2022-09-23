package fr.ensicaen.ensibrary.libraryauthapi.repository;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
