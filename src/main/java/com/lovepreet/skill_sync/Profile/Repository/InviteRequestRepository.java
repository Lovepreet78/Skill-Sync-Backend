package com.lovepreet.skill_sync.Profile.Repository;

import com.lovepreet.skill_sync.Profile.Model.InviteRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InviteRequestRepository extends JpaRepository<InviteRequests, Long> {

    // Find all invite requests where the sender has the given user ID
    List<InviteRequests> findBySenderId(Long senderId);

    // Find all invite requests where the receiver has the given user ID
    List<InviteRequests> findByReceiverId(Long receiverId);

//    Optional<InviteRequests> saveInviteRequest(InviteRequests inviteRequest);
}
