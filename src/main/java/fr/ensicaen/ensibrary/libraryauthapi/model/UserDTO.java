package fr.ensicaen.ensibrary.libraryauthapi.model;

import fr.ensicaen.ensibrary.libraryauthapi.entity.User;

public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public User toEntity() {
        User user = new User();
        user.setEmail(user.getEmail());
        user.setEmail(user.getFirstName());
        user.setEmail(user.getLastName());
        return user;
    }
}
