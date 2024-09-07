package com.lovepreet.skill_sync.JWT;

import com.lovepreet.skill_sync.SecurityConfig.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginCont {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        String token = userService.verify(user);

        if (!"failed".equals(token)) {
            LoginTokenAndUsername loginTokenAndUser = new LoginTokenAndUsername();
            loginTokenAndUser.setToken(token);
            loginTokenAndUser.setUsername(user.getUsername());
            return ResponseEntity.ok(loginTokenAndUser);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}

