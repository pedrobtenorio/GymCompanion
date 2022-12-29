package com.pedro.GymCompanion.Service;

import com.pedro.GymCompanion.Domain.AuthResponse;
import com.pedro.GymCompanion.Domain.User;
import com.pedro.GymCompanion.Repository.UserRepository;
import com.pedro.GymCompanion.Security.Credential;
import com.pedro.GymCompanion.Security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    final private PasswordEncoder passwordEncoder;
    final private UserRepository userRepository;

    final private AuthenticationManager authManager;
    final private JwtTokenUtil jwtTokenUtil;




    @Autowired
    public UserService (PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public User save(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(this.encodePassword(user.getPassword()));

        return userRepository.save(user);
    }

    public User findById(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public ResponseEntity<AuthResponse> authenticate(Credential credential) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credential.getEmail(), credential.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
