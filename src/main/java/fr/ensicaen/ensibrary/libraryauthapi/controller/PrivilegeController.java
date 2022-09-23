package fr.ensicaen.ensibrary.libraryauthapi.controller;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Privilege;
import fr.ensicaen.ensibrary.libraryauthapi.service.PrivilegeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@PreAuthorize("hasAuthority('PRIVILEGE_MODIFY')")
@RequestMapping("privilege")
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all privileges")
    public Collection<Privilege> getAll() {
        return privilegeService.getAll();
    }
}
