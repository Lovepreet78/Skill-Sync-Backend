package com.lovepreet.skill_sync.SecurityConfig;


//import com.lovepreet.skill_sync.SecurityConfig.AuthController.CustomAuthenticationSuccessHandler;
import com.lovepreet.skill_sync.SecurityConfig.AuthController.CustomUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder);
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(registry -> {
                registry.requestMatchers("/login","/register").permitAll();
                registry.anyRequest().authenticated();
            })
            .formLogin(formLogin ->
                    formLogin
                            .loginProcessingUrl("/login")
                            .successHandler((request, response, authentication) -> {
                                response.setStatus(HttpServletResponse.SC_OK);
                                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                                response.getWriter().write("{\"username\":\"" + userDetails.getUsername() + "\", \"id\":" + userDetails.getId() + "}");
                            })
                            .failureHandler((request, response, exception) -> {

                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                                response.getWriter().write("{\"error\":\"" + exception.getMessage() + "\"}");
                            })
                            .permitAll()

            )
            .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                    .deleteCookies("JSESSIONID"))
            .httpBasic(Customizer.withDefaults());
    return http.build();
}

}