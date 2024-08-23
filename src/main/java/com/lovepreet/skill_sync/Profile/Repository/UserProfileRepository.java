package com.lovepreet.skill_sync.Profile.Repository;

import com.lovepreet.skill_sync.Profile.Model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByEmail(String email);

    UserProfile findByUsername(String username);
}