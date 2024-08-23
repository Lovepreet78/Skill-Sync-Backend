package com.lovepreet.skill_sync.Profile.Controllers;

import com.lovepreet.skill_sync.Profile.Model.UserProfile;
import com.lovepreet.skill_sync.Profile.Service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    // Create or update a user profile
    @PostMapping("/save")
    public ResponseEntity<UserProfile> saveUserProfile(@RequestBody UserProfile userProfile) {

        UserProfile savedProfile = userProfileService.saveUserProfile(userProfile);
        if(savedProfile != null) {
            return ResponseEntity.ok(savedProfile);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    // Get all user profiles
    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> getAllUserProfiles() {
        List<UserProfile> profiles = userProfileService.getAllUserProfiles();
        return ResponseEntity.ok(profiles);
    }

    // Get a user profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Long id) {
        Optional<UserProfile> userProfile = userProfileService.getUserProfileById(id);
        return userProfile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get a user profile by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserProfile> getUserProfileByEmail(@PathVariable String email) {
        Optional<UserProfile> userProfile = userProfileService.getUserProfileByEmail(email);
        return userProfile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a user profile by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }
}
