package com.sda.clinic.models.company.medical_history;

import com.sda.clinic.models.company.CompanyBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "sick_leaves")
@SQLDelete(sql = "UPDATE sick_leavess SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class SickLeave extends CompanyBase {

    private String series;
    @Column(name = "patent_number")
    private String number;
    private String pesel;
    private String patient;

    @Enumerated(EnumType.STRING)
    private InsuredType insuredType;
    private LocalDate birthDayInsured;
    private String postCode;
    private String post;
    private String street;
    private String streetNo;
    private String flatNo;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate hospitalStartDate;
    private LocalDate hospitalEndDate;

    @Enumerated(EnumType.STRING)
    private IndicationType indicationType;

    @Enumerated(EnumType.STRING)
    private IncapacityCode code1;
    private IncapacityCode code2;
    private IncapacityCode code3;
    private IncapacityCode code4;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_type")
    private DiseaseType diseaseType;

}
