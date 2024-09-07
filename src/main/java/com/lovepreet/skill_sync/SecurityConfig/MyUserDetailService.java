package com.lovepreet.skill_sync.SecurityConfig;

import com.lovepreet.skill_sync.SecurityConfig.AuthController.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> myUserOptional = myUserRepository.findByUsername(username);
        System.out.println("called : "+username);
        if (myUserOptional.isPresent()) {

            User user = myUserOptional.get();
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

















//@Service
//public class MyUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private MyUserRepository myUserRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> myUserOptional = myUserRepository.findByUsername(username);
//        if (myUserOptional.isPresent()) {
//            User user = myUserOptional.get();
//
//            // Convert roles to String array
//            String[] roles = user.getRoles().stream()
//                    .map(Role::name)  // Convert Enum to String
//                    .toArray(String[]::new);
//
//            // Return a custom UserDetails implementation
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    Arrays.stream(roles)
//                            .map(SimpleGrantedAuthority::new)  // Map each role to SimpleGrantedAuthority
//                            .collect(Collectors.toList())
//            );
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//    }
//}
