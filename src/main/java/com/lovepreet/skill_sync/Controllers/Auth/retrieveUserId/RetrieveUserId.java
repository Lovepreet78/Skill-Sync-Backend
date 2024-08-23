package com.lovepreet.skill_sync.Controllers.Auth.retrieveUserId;

import com.lovepreet.skill_sync.Profile.Repository.UserProfileRepository;
import com.lovepreet.skill_sync.SecurityConfig.MyUserRepository;
import com.lovepreet.skill_sync.SecurityConfig.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RetrieveUserId {


    MyUserRepository myUserRepository;
    public RetrieveUserId(MyUserRepository myUserRepository){
        this.myUserRepository = myUserRepository;
    }
    @GetMapping("/userIdByUsername")
    public ResponseEntity<Long> retrieveUserId(@RequestBody String username) {
        Optional<User> user = myUserRepository.findByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().getId());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
