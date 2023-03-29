package com.sda.clinic.repository;

import com.sda.clinic.models.company.role.RoleType;
import com.sda.clinic.models.company.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);

}
