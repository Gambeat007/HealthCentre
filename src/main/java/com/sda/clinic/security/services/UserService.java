package com.sda.clinic.security.services;

import com.sda.clinic.constants.Constants;
import com.sda.clinic.models.company.DictionaryItemsDto;
import com.sda.clinic.models.company.clinic.Clinic;
import com.sda.clinic.models.company.role.RoleType;
import com.sda.clinic.models.company.user.User;
import com.sda.clinic.models.company.user.UserDto;
import com.sda.clinic.repository.ClinicRepository;
import com.sda.clinic.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ClinicRepository clinicRepository;

    public Page<UserDto> getAllUsers(int page) {
        return userRepository.findAll(PageRequest.of(page, Constants.DefaultPageSize)).map(UserDto::map);
    }

    @Transactional
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.equals("")) {
            throw new UsernameNotFoundException("User Not Found.");
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found."));

        return UserDto.map(user);
    }

    public void modify(UserDto request, PasswordEncoder encoder) {
        final User user = userRepository
                .findByUuid(UUID.fromString(request.getUuid()))
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika!!!"));
        final Clinic clinic = clinicRepository
                .findByUuid(UUID.fromString(request.getClinic()))
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika!!!"));
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(encoder.encode(request.getPassword()));
        }
        user.setEmail(request.getEmail());
        user.getUserAppDetails().setFirstName(request.getUserAppDetails().getFirstName());
        user.getUserAppDetails().setSecondName(request.getUserAppDetails().getSecondName());
        user.getUserAppDetails().setLastName(request.getUserAppDetails().getLastName());
        user.getUserAppDetails().setPesel(request.getUserAppDetails().getPesel());
        user.getUserAppDetails().setIdNumber(request.getUserAppDetails().getIdNumber());
        user.getUserAppDetails().setBirthDay(request.getUserAppDetails().getBirthDay());
        user.getUserAppDetails().setNip(request.getUserAppDetails().getNip());
        user.setClinic(clinic);
        userRepository.saveAndFlush(user);
    }

    public DictionaryItemsDto getPatientsAsDictionary() {
        return new DictionaryItemsDto(userRepository.findByRole(RoleType.ROLE_PATIENT).stream()
                .map(UserDto::dictionary)
                .collect(Collectors.toList()));
    }

    public DictionaryItemsDto getDoctorsAsDictionary() {
        return new DictionaryItemsDto(userRepository.findByRole(RoleType.ROLE_DOCTOR).stream()
                .map(UserDto::dictionary)
                .collect(Collectors.toList()));
    }

    public void deleteByUuid(UUID uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("Nie " +
                "znaleziono użytkownika!!!"));

        userRepository.delete(user);
    }
}
