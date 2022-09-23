package fr.ensicaen.ensibrary.libraryauthapi.service;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Role;
import fr.ensicaen.ensibrary.libraryauthapi.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Collection<Role> getAll() {
        return roleRepository.findAll();
    }
}
