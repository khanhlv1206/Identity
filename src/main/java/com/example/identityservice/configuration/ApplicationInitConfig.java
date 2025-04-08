package com.example.identityservice.configuration;

import com.example.identityservice.entity.User;
import com.example.identityservice.enums.Role;
import com.example.identityservice.repository.UserRepository;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
@Configuration
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
@Bean
@ConditionalOnProperty(prefix = "spring",value = "datasource.driverClassname"
        ,havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository) {
    log.info("Init Application Runner");
    return args -> {
        if (userRepository.findByUsername("admin").isEmpty()) {
            var roles = new HashSet<String>();
            roles.add(Role.ADMIN.name);

            User user = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
             //       .roles(roles)
                    .build();
            userRepository.save(user);
            log.warn("admin user has been created with default password:admin, please change it");
        }
    };
}
}
