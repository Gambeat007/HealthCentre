package com.sda.clinic.controllers;

import com.sda.clinic.models.GetUserResponse;
import com.sda.clinic.models.company.DictionaryItemsDto;
import com.sda.clinic.models.company.user.UserDto;
import com.sda.clinic.repository.UserRepository;
import com.sda.clinic.security.services.UserDetailsImpl;
import com.sda.clinic.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam Integer page) {
        return ResponseEntity.ok(userService.getAllUsers(page));
    }

    @GetMapping("/getUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_SECRETARY', 'ROLE_PATIENT')")
    public ResponseEntity<GetUserResponse> getCurrentUser(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new GetUserResponse(userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @GetMapping("/getUserDetails")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_SECRETARY', 'ROLE_PATIENT')")
    public ResponseEntity<UserDto> getCurrentUserDetails(@RequestParam String username) {
        return ResponseEntity.ok(userService.loadUserByUsername(username));
    }

    @PutMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_SECRETARY', 'ROLE_PATIENT')")
    public ResponseEntity<?> modify(@RequestBody UserDto request) {
        userService.modify(request, encoder);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/getDictionaryPatients")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_SECRETARY', 'ROLE_PATIENT')")
    public ResponseEntity<DictionaryItemsDto> getDictionaryPatients() {
        return ResponseEntity.ok(userService.getPatientsAsDictionary());
    }

    @GetMapping("/getDictionaryDoctors")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_SECRETARY', 'ROLE_PATIENT')")
    public ResponseEntity<DictionaryItemsDto> getDictionaryDoctors() {
        return ResponseEntity.ok(userService.getDoctorsAsDictionary());
    }

    @DeleteMapping("/delete{uuid}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("uuid") UUID uuid) {
        userService.deleteByUuid(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
