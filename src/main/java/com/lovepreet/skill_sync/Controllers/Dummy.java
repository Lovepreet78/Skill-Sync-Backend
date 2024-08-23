package com.lovepreet.skill_sync.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Dummy {
    @GetMapping("/hii")
    public String dd(){
        return "lovepreet";
    }
}
