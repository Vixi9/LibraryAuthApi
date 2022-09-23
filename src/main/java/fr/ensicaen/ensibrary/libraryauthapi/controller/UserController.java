package fr.ensicaen.ensibrary.libraryauthapi.controller;

import fr.ensicaen.ensibrary.libraryauthapi.entity.User;
import fr.ensicaen.ensibrary.libraryauthapi.exception.UserNotFoundException;
import fr.ensicaen.ensibrary.libraryauthapi.model.UserDTO;
import fr.ensicaen.ensibrary.libraryauthapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping(value = "user")
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

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all users")
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.get(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping(value = "/")
    @Operation(summary = "Create new user")
    public ResponseEntity<Object> add(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update user by id")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(userService.update(id, user));
        } catch (IllegalArgumentException | UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

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
