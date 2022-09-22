package fr.ensicaen.ensibrary.libraryauthapi.service;

import fr.ensicaen.ensibrary.libraryauthapi.entity.User;
import fr.ensicaen.ensibrary.libraryauthapi.exception.UserNotFoundException;
import fr.ensicaen.ensibrary.libraryauthapi.model.UserDTO;
import fr.ensicaen.ensibrary.libraryauthapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private static final String BAD_EMAIL_MESAGE = "Invalid email : ";

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    public User get(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Transactional
    public User add(UserDTO user) throws IllegalArgumentException {
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException(BAD_EMAIL_MESAGE + user.getEmail());
        }
        return userRepository.save(user.toEntity());
    }

    @Transactional
    public User update(Long id, UserDTO user) throws UserNotFoundException {
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException(BAD_EMAIL_MESAGE + user.getEmail());
        }
        User newUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        return userRepository.save(newUser);
    }
}
