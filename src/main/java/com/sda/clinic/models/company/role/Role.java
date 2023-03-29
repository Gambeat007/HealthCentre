package com.sda.clinic.models.company.role;

import com.sda.clinic.models.company.CompanyBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class Role extends CompanyBase {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private RoleType name;

}
