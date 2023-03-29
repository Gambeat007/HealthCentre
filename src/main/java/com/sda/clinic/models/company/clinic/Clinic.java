package com.sda.clinic.models.company.clinic;

import com.sda.clinic.models.company.Company;
import com.sda.clinic.models.company.CompanyBase;
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
@Table(name = "clinics")
@SQLDelete(sql = "UPDATE clinics SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class Clinic extends CompanyBase {

    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "uuid", nullable = false)
    private Company company;

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
    @Column(name = "clinic_name")
    private String clinicName;
    private String description;

    private float longitude;
    private float latitude;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClinicEmail> emails;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClinicPhone> phones;

}
