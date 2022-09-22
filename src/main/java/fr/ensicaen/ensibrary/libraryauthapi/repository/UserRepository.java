package fr.ensicaen.ensibrary.libraryauthapi.repository;

import fr.ensicaen.ensibrary.libraryauthapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
