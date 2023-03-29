package com.sda.clinic.repository;

import com.sda.clinic.models.company.role.RoleType;
import com.sda.clinic.models.company.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUuid(UUID uuid);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :role_name")
    Set<User> findByRole(@Param("role_name") RoleType role_name);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE u.uuid = :uuid AND r.name = :role_name")
    Optional<User> findByUuidAndRole(@Param("uuid") UUID uuid, @Param("role_name") RoleType role_name);

}
