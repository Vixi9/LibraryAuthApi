package fr.ensicaen.ensibrary.libraryauthapi.exception;

public class UserNotFoundException extends Exception {

    private static final String USER_NOT_FOUND_MESSAGE = "Can not find user with id ";

    public UserNotFoundException(Long id) {
        super(USER_NOT_FOUND_MESSAGE + id);
    }
}
