package com.sda.clinic.models.company.clinic;

import com.sda.clinic.models.company.CompanyBase;
import com.sda.clinic.models.company.PhoneType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "clinics_phones")
@SQLDelete(sql = "UPDATE clinics_phones SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class ClinicPhone extends CompanyBase {

    @ManyToOne
    @JoinColumn(name = "clinic", referencedColumnName = "uuid", nullable = false)
    private Clinic clinic;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_type")
    private PhoneType phoneType;
    private String phone;
    @Column(name = "is_primary")
    private boolean primary;
}
