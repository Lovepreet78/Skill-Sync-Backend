package com.lovepreet.skill_sync.JWT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginTokenAndUsername {
    private String token;
    private String username;

}
