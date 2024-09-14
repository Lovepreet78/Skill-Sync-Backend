package com.lovepreet.skill_sync.Controllers.RegisterUser;

import com.lovepreet.skill_sync.SecurityConfig.user.MyUserRepository;
import com.lovepreet.skill_sync.SecurityConfig.user.Role;
import com.lovepreet.skill_sync.SecurityConfig.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new ResponseEntity<>("User already exists",HttpStatus.FORBIDDEN);
        }
        else if(request.getPassword().length() < 8 || request.getUsername().isEmpty() || request.getUsername().isBlank()) {
            return new ResponseEntity<>("Credential Constraint unsatisfied", HttpStatus.FORBIDDEN);
        }

        User newUser = new User();

        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoles(Set.of(Role.USER));

        userRepository.save(newUser);
        return new ResponseEntity<>("User Registered Successfully",HttpStatus.CREATED);

    }
}