package com.sda.clinic.repository;

import com.sda.clinic.models.company.user.UserAppDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserAppDetails, UUID> {
}
