package com.lovepreet.skill_sync.Profile.Service;

import com.lovepreet.skill_sync.Profile.Model.UserProfile;
import com.lovepreet.skill_sync.Profile.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    // Create or update a user profile
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    // Retrieve all user profiles
    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    // Retrieve a user profile by ID
    public Optional<UserProfile> getUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    // Retrieve a user profile by email
    public Optional<UserProfile> getUserProfileByEmail(String email) {
        return Optional.ofNullable(userProfileRepository.findByEmail(email));
    }

    // Delete a user profile by ID
    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
}

