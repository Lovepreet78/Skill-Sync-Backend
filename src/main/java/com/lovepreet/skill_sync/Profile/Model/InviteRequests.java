package com.lovepreet.skill_sync.Profile.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InviteRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    // Storing the user id as sender and receiver
    private Long senderId;
    private Long receiverId;

    private String status;  // E.g., "Pending", "Accepted", "Rejected"
    private LocalDateTime sentDate;
    private String message;
}
