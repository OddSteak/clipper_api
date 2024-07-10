package org.clipper.controllers;

import org.clipper.models.AuthRequest;
import org.clipper.models.AuthResponse;
import org.clipper.websecurity.JwtUtil;
import org.clipper.websecurity.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    // standard token spring mvc uses for username and password
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid username or password");
            throw new Exception("incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsServiceImpl
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
