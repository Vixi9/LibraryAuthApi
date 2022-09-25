package fr.ensicaen.ensibrary.libraryauthapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class JDBCAuthenticationConfig {

    private static final String USERS_BY_USERNAME_QUERY = "SELECT email, password, enabled FROM users WHERE email = ?";
    private static final String AUTHORITIES_BY_USERNAME_QUERY = "SELECT u.email, r.name FROM users u JOIN user_roles ur ON u.id = ur.user_id JOIN role r ON r.id = ur.role_id WHERE email = ?";
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
