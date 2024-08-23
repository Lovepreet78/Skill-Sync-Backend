package com.lovepreet.skill_sync.Profile.Model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    @Column(unique = true)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String username;
    private String major;
    private String profession;
    private String university;
    private String bio;
    private boolean isAvailableToJoin;
    @ElementCollection
    private List<String> skills;
}
