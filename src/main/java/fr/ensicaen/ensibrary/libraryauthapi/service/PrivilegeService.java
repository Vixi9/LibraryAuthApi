package fr.ensicaen.ensibrary.libraryauthapi.service;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Privilege;
import fr.ensicaen.ensibrary.libraryauthapi.repository.PrivilegeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public Collection<Privilege> getAll() {
        return privilegeRepository.findAll();
    }
}
