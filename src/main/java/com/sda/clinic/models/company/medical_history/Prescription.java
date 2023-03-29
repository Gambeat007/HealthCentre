package com.sda.clinic.models.company.medical_history;

import com.sda.clinic.models.company.CompanyBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "prescriptions")
@SQLDelete(sql = "UPDATE prescriptions SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class Prescription extends CompanyBase {

    private String code;
    @Column(name = "access_code")
    private String accessCode;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    private String patient;
    private String doctor;
    private String pzw;
    private String phone;
    @Column(name = "control_date")
    private LocalDateTime controlDate;
    @Column(name = "prescriptions_number")
    private String number;
    private String drug;
    @Column(name = "realization_date")
    private LocalDate realizationDate;
    @Column(name = "packaging_type")
    private String packagingType;
    private String dosage;
}