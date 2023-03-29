package com.sda.clinic.models.company.doctor;

import com.sda.clinic.models.company.CompanyBase;
import com.sda.clinic.models.company.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "doctors")
@SQLDelete(sql = "UPDATE doctors SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class Doctor extends CompanyBase {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;
    private String pzw;
    @Enumerated(EnumType.STRING)
    @Column(name = "academic_title")
    private AcademicTitle academicTitle;
}
