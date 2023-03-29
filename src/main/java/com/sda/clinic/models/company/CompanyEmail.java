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
@Table(name = "company_emails")
@SQLDelete(sql = "UPDATE company_emails SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class CompanyEmail extends CompanyBase {

    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "uuid", nullable = false)
    private Company company;
    private String email;
    @Column(name = "is_primary")
    private boolean primary;
}
