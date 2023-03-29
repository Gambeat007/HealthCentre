package com.sda.clinic.models.company;

import com.sda.clinic.models.company.clinic.Clinic;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "company")
@SQLDelete(sql = "UPDATE company SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class Company extends CompanyBase {

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "short_name")
    private String shortName;

    private String nip;
    private String regon;
    private String krs;
    @Column(name = "account_number")
    private String accountNumber;
    private String province;
    private String district;
    private String community;
    private String locality;
    private String street;
    @Column(name = "street_no")
    private String streetNo;
    @Column(name = "flat_no")
    private String flatNo;
    @Column(name = "post_code")
    private String postCode;
    private String post;

    private float longitude;
    private float latitude;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompanyEmail> emails;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CompanyPhone> phones;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Clinic> clinics;
}

