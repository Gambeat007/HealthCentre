package com.sda.clinic.models.company.clinic;

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
@Table(name = "clinics_emails")
@SQLDelete(sql = "UPDATE clinics_emails SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class ClinicEmail extends CompanyBase {

    @ManyToOne
    @JoinColumn(name = "clinic", referencedColumnName = "uuid", nullable = false)
    private Clinic clinic;
    private String email;
    @Column(name = "is_primary")
    private boolean primary;
}
