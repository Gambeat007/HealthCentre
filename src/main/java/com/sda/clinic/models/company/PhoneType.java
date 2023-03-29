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
@Table(name = "phone_types")
@SQLDelete(sql = "UPDATE phone_types SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class PhoneType extends CompanyBase {

    @Column(name = "phone_type")
    private String type;
}