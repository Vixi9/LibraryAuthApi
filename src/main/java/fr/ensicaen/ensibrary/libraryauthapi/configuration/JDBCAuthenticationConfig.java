package fr.ensicaen.ensibrary.libraryauthapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class JDBCAuthenticationConfig {

    private static final String USERS_BY_USERNAME_QUERY = "SELECT email, password, enabled FROM users WHERE email = ?";
    private static final String AUTHORITIES_BY_USERNAME_QUERY = "SELECT usr.email, pvl.name\n" +
            "FROM users usr\n" +
            "         JOIN user_roles rol ON usr.id = rol.user_id\n" +
            "         JOIN role_privileges rol_pvl ON rol.role_id = rol_pvl.role_id\n" +
            "         JOIN privilege pvl ON rol_pvl.privilege_id = pvl.id\n" +
            "WHERE email = ?";
    private final DataSource dataSource;

    public JDBCAuthenticationConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USERS_BY_USERNAME_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
