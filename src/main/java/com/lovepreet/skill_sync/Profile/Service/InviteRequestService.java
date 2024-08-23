package com.lovepreet.skill_sync.Profile.Service;

import com.lovepreet.skill_sync.Profile.Model.InviteRequests;
import com.lovepreet.skill_sync.Profile.Repository.InviteRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InviteRequestService {

    @Autowired
    private InviteRequestRepository inviteRequestRepository;

    // Get invites sent by a user (using senderId)
    public List<InviteRequests> getSentInvites(Long senderId) {
        return inviteRequestRepository.findBySenderId(senderId);
    }

    // Get invites received by a user (using receiverId)
    public List<InviteRequests> getReceivedInvites(Long receiverId) {
        return inviteRequestRepository.findByReceiverId(receiverId);
    }
}

