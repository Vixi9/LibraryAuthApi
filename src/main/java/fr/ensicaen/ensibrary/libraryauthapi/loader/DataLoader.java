package fr.ensicaen.ensibrary.libraryauthapi.loader;

import fr.ensicaen.ensibrary.libraryauthapi.entity.Role;
import fr.ensicaen.ensibrary.libraryauthapi.entity.User;
import fr.ensicaen.ensibrary.libraryauthapi.repository.RoleRepository;
import fr.ensicaen.ensibrary.libraryauthapi.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_LIBRARIAN"));
        roleRepository.save(new Role("ROLE_AGENT"));
        roleRepository.save(new Role("ROLE_USER"));

        User user = new User("admin@email.com", "admin", "admin", "$2y$10$MsjSF2MdinfKDTmFosyWJen9oCIb4Pv99qIyHJANDgPbWA62YlGRu", true);
        user.getRoles().add(roleRepository.getAdminRole());
        userRepository.save(user);
    }
}
