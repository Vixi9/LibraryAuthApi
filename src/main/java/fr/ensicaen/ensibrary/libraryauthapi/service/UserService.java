package fr.ensicaen.ensibrary.libraryauthapi.service;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Role;
import fr.ensicaen.ensibrary.libraryauthapi.entity.User;
import fr.ensicaen.ensibrary.libraryauthapi.exception.UserNotFoundException;
import fr.ensicaen.ensibrary.libraryauthapi.model.UserDTO;
import fr.ensicaen.ensibrary.libraryauthapi.repository.RoleRepository;
import fr.ensicaen.ensibrary.libraryauthapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private static final String INVALID_EMAIL = "Invalid email : ";

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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

    public User createLibrarian(UserDTO user) {
        User userEntity = user.toEntity();
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.getLibrarianRole();
        userEntity.getRoles().add(role);
        return userRepository.save(userEntity);
    }

    public User createAgent(UserDTO user) {
        User userEntity = user.toEntity();
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.getAgentRole();
        userEntity.getRoles().add(role);
        return userRepository.save(userEntity);
    }

    public User createUser(UserDTO user) {
        User userEntity = user.toEntity();
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEnabled(true);
        Role role = roleRepository.getUserRole();
        userEntity.getRoles().add(role);
        return userRepository.save(userEntity);
    }

    @Transactional
    public User update(Long id, UserDTO user) throws UserNotFoundException {
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException(INVALID_EMAIL + user.getEmail());
        }
        User newUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        return userRepository.save(newUser);
    }

    public void delete(Long id) throws UserNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
