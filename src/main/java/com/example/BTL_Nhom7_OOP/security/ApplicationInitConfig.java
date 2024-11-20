package com.example.BTL_Nhom7_OOP.security;

import com.example.BTL_Nhom7_OOP.constant.PredefinedRole;
import com.example.BTL_Nhom7_OOP.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class   ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    // Tạo tài khoản admin nếu chưa có
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUsername(PredefinedRole.ADMIN_ROLE).isEmpty()) {

                // Code ...
                log.warn("Admin user has been created with default password: admin. Please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
