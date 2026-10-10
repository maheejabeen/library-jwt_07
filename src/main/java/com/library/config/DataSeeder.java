package com.library.config;

import com.library.entity.User;
import com.library.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Creates default test users on first start. */
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            create(repo, encoder, "admin", "admin123", "ADMIN");
            create(repo, encoder, "librarian", "lib123", "LIBRARIAN");
            create(repo, encoder, "user", "user123", "USER");
        };
    }

    private void create(UserRepository repo, PasswordEncoder enc, String u, String p, String role) {
        if (repo.findByUsername(u).isEmpty()) {
            repo.save(new User(u, enc.encode(p), role));
        }
    }
}
