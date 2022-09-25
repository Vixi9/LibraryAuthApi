package fr.ensicaen.ensibrary.libraryauthapi.model;

import fr.ensicaen.ensibrary.libraryauthapi.entity.User;

public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User toEntity() {
        User user = new User();
        user.setEmail(this.getEmail());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        return user;
    }
}
