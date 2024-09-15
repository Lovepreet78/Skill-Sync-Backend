package com.lovepreet.skill_sync.Profile.Controllers;

import com.lovepreet.skill_sync.Profile.Model.UserProfile;
import com.lovepreet.skill_sync.Profile.Service.UserProfileService;
import com.lovepreet.skill_sync.Profile.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UploadImage uploadImage;
    // Create or update a user profile
    @PostMapping("/save")
    public ResponseEntity<UserProfile> saveUserProfile(@RequestPart("userProfile") UserProfile userProfile,
                                                       @RequestPart(value = "profileImage", required = false) MultipartFile profileImage) {

        try {
            // Check if an image is provided
            if (profileImage != null && !profileImage.isEmpty()) {
                // Call the method to upload the image to FreeImage API and get the URL
                String imageUrl = uploadImage.uploadImageToFreeImageAPI(profileImage);
                userProfile.setImageUrl(imageUrl); // Set the image URL in the UserProfile
            }

            // Save the user profile
            UserProfile savedProfile = userProfileService.saveUserProfile(userProfile);
            if (savedProfile != null) {
                return ResponseEntity.ok(savedProfile);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
