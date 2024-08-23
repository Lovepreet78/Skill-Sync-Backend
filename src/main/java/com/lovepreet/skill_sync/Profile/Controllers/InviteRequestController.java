package com.lovepreet.skill_sync.Profile.Controllers;

import com.lovepreet.skill_sync.Profile.Model.InviteRequests;
import com.lovepreet.skill_sync.Profile.Service.InviteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/invites")
public class InviteRequestController {

    @Autowired
    private InviteRequestService inviteRequestService;

    @GetMapping("/sent")
    public List<InviteRequests> getSentInvites(@RequestParam Long userId) {
        return inviteRequestService.getSentInvites(userId);
    }

    @GetMapping("/received")
    public List<InviteRequests> getReceivedInvites(@RequestParam Long userId) {
        return inviteRequestService.getReceivedInvites(userId);
    }
}
