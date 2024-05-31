package com.lux.services;

import com.lux.dto.AuthDTO;
import com.lux.models.user.Auth;
import com.lux.models.user.Roles;
import com.lux.models.user.Users;
import com.lux.repositories.AuthRepository;
import com.lux.repositories.RolesRepository;
import com.lux.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;

@Service
public class AuthService {

    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final SecureRandom random = new SecureRandom();
    private final AuthRepository authRepository;
    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;
    // Создаем объект BCryptPasswordEncoder
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(AuthRepository authRepository, RolesRepository rolesRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public AuthDTO createAuth(String login, Long userId) {

        Auth auth = new Auth();
        auth.setLogin(login);
        auth.setPassword(generatePassword());
        String password = auth.getPassword();
        auth.setPassword(encoder.encode(auth.getPassword()));

        Roles role = rolesRepository.findById(2L).orElseThrow(() -> new RuntimeException("Role not found"));
        auth.setRole(role);

        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        auth.setUser(user);


        authRepository.save(auth);
        return new AuthDTO(auth.getLogin(), password);
    }

    private String generatePassword() {
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
