package com.sda.clinic.models.company;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "company_phones")
@SQLDelete(sql = "UPDATE company_phones SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class CompanyPhone extends CompanyBase {

    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "uuid", nullable = false)
    private Company company;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_type")
    private PhoneType phoneType;
    private String phone;
    @Column(name = "is_primary")
    private boolean primary;
}
