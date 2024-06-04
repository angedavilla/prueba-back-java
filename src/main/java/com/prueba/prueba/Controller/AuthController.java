package com.prueba.prueba.Controller;

import com.prueba.prueba.Entities.JwtResponse;
import com.prueba.prueba.Entities.UserEntities;
import com.prueba.prueba.Service.JwtUserDetailsService;
import com.prueba.prueba.Service.UserService;
import com.prueba.prueba.Util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntities> getCurrentUser(Principal principal){
        UserEntities userEntities = userService.getUserById(Long.valueOf(principal.getName()));
        return ResponseEntity.ok(userEntities);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntities> registerUser(@RequestBody UserEntities userEntities){
        userEntities.setPassword(new BCryptPasswordEncoder().encode(userEntities.getPassword()));
        UserEntities savedUser = userService.saveUser(userEntities);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserEntities userEntities) throws Exception {
        try {
            System.out.println("Attempting to authenticate user: " + userEntities.getUsername());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntities.getUsername(), userEntities.getPassword()));
            System.out.println("Authentication successful for user: " + userEntities.getUsername());
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed for user: " + userEntities.getUsername());
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userEntities.getUsername());
        System.out.println("User details loaded: " + userDetails.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("Generated Token: " + token);

        JwtResponse jwtResponse = new JwtResponse(token);
        System.out.println("JwtResponse Token: " + jwtResponse.getToken());

        return ResponseEntity.ok(jwtResponse);
    }
}
