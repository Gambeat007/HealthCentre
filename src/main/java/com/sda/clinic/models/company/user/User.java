package com.sda.clinic.models.company.user;

import com.sda.clinic.models.company.CompanyBase;
import com.sda.clinic.models.company.clinic.Clinic;
import com.sda.clinic.models.company.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@SQLDelete(sql = "UPDATE users SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class User extends CompanyBase {

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(min = 5, max = 50)
    @Email
    private String email;

    @NotBlank
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private UserAppDetails userAppDetails;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_uuid"),
            inverseJoinColumns = @JoinColumn(name = "role_uuid"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_uuid")
    private Clinic clinic;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
