package com.lovepreet.skill_sync.Profile.Controllers;

import com.lovepreet.skill_sync.Profile.Model.InviteRequests;
import com.lovepreet.skill_sync.Profile.Repository.InviteRequestRepository;
import com.lovepreet.skill_sync.Profile.Service.InviteRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invites")
public class InviteRequestController {

    @Autowired
    private InviteRequestService inviteRequestService;
    @Autowired
    private InviteRequestRepository inviteRequestRepository;

    @GetMapping("/sent")
    public List<InviteRequests> getSentInvites(@RequestParam Long userId) {
        return inviteRequestService.getSentInvites(userId);
    }

    @GetMapping("/received")
    public List<InviteRequests> getReceivedInvites(@RequestParam Long userId) {
        return inviteRequestService.getReceivedInvites(userId);
    }
    @GetMapping("/all")
    public List<InviteRequests> getAllInvites() {
        return inviteRequestRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<InviteRequests> saveInvite(@RequestBody InviteRequests inviteRequests) {
        return ResponseEntity.ok(inviteRequestService.save(inviteRequests));
    }

//    @PutMapping("/update")
//    public ResponseEntity<InviteRequests> updateInvite(@RequestBody InviteRequests inviteRequests) {
//        InviteRequests updatedInvite = inviteRequestService.updateInvite(inviteRequests);
//        return ResponseEntity.ok(updatedInvite);
//    }

    @PutMapping("/update")
    public InviteRequests updateInvite(@RequestBody InviteRequests inviteRequest) {
        Optional<InviteRequests> existingInvite = inviteRequestService.findById(inviteRequest.getUserId());


        if (existingInvite.isPresent()) {
            InviteRequests inviteToUpdate = existingInvite.get();
            inviteToUpdate.setStatus(inviteRequest.getStatus());
            return inviteRequestService.save(inviteToUpdate);
        } else {
            throw new IllegalArgumentException("Invite not found for id: " + inviteRequest.getUserId());
        }
    }
}
