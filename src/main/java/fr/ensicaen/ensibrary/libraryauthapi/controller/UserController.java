package fr.ensicaen.ensibrary.libraryauthapi.controller;

import fr.ensicaen.ensibrary.libraryauthapi.entity.User;
import fr.ensicaen.ensibrary.libraryauthapi.exception.UserNotFoundException;
import fr.ensicaen.ensibrary.libraryauthapi.model.UserDTO;
import fr.ensicaen.ensibrary.libraryauthapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.get(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> add(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.add(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok().body(userService.update(id, user));
        } catch (IllegalArgumentException | UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
