package fr.ensicaen.ensibrary.libraryauthapi.controller;

import fr.ensicaen.ensibrary.libraryauthapi.entity.User;
import fr.ensicaen.ensibrary.libraryauthapi.exception.UserNotFoundException;
import fr.ensicaen.ensibrary.libraryauthapi.model.UserDTO;
import fr.ensicaen.ensibrary.libraryauthapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping(value = "user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/principal")
    @Operation(summary = "Get authenticated user information")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }

    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN", "ROLE_AGENT"})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all users")
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN", "ROLE_AGENT"})
    @GetMapping(value = "/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.get(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN", "ROLE_AGENT"})
    @PostMapping(value = "/user/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostAuthorize("hasAuthority('USER_CREATE')")
    @Operation(summary = "Create new user")
    public ResponseEntity<Object> createUser(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @PostMapping(value = "/agent/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostAuthorize("hasAuthority('AGENT_CREATE')")
    @Operation(summary = "Create new agent")
    public ResponseEntity<Object> createAgent(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(userService.createAgent(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @PostMapping(value = "/librarian/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostAuthorize("hasAuthority('LIBRARIAN_CREATE')")
    @Operation(summary = "Create new librarian")
    public ResponseEntity<Object> createLibrarian(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(userService.createLibrarian(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update user by id")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(userService.update(id, user));
        } catch (IllegalArgumentException | UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_LIBRARIAN"})
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete user by id")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok(null);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
