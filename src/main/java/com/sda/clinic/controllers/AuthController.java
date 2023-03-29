package com.sda.clinic.controllers;

import com.sda.clinic.models.company.role.RoleType;
import com.sda.clinic.models.company.role.Role;
import com.sda.clinic.models.company.user.User;
import com.sda.clinic.models.company.user.UserAppDetails;
import com.sda.clinic.payload.request.LoginRequest;
import com.sda.clinic.payload.request.SignupRequest;
import com.sda.clinic.payload.response.JwtResponse;
import com.sda.clinic.payload.response.MessageResponse;
import com.sda.clinic.repository.RoleRepository;
import com.sda.clinic.repository.UserDetailsRepository;
import com.sda.clinic.repository.UserRepository;
import com.sda.clinic.security.jwt.JwtUtils;
import com.sda.clinic.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_PATIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "doctor" -> {
                        Role doctorRole = roleRepository.findByName(RoleType.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(doctorRole);
                    }
                    case "secretary" -> {
                        Role secreteryRole = roleRepository.findByName(RoleType.ROLE_SECRETARY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(secreteryRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(RoleType.ROLE_PATIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        User newUser = new User();
        newUser = userRepository.findByUsername(signUpRequest.getUsername()).orElseThrow(() -> new RuntimeException(
                "Error: User is not found."));
        UserAppDetails userAppDetails = new UserAppDetails();
        userAppDetails.setUser(newUser);

        userDetailsRepository.saveAndFlush(userAppDetails);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
