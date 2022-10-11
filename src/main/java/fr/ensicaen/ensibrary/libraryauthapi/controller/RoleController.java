package fr.ensicaen.ensibrary.libraryauthapi.controller;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Role;
import fr.ensicaen.ensibrary.libraryauthapi.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all roles")
    public Collection<Role> getAll() {
        return roleService.getAll();
    }
}
