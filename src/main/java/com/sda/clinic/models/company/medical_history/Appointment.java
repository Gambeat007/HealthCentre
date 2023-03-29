package com.sda.clinic.models.company.medical_history;

import com.sda.clinic.models.company.CompanyBase;
import com.sda.clinic.models.company.clinic.Clinic;
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
@Table(name = "appointments")
@SQLDelete(sql = "UPDATE appointments SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class Appointment extends CompanyBase {

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "patient")
    private User patient;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "doctor")
    private User doctor;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "clinic")
    private Clinic clinic;

    private String description;
    private String recommendations;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sick_leave")
    private SickLeave sickLeave;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription")
    private Prescription prescription;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referral")
    private Referral referral;
}