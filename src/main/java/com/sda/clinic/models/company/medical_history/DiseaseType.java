package com.sda.clinic.models.company.medical_history;

import com.sda.clinic.models.company.CompanyBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "disease_types")
@SQLDelete(sql = "UPDATE disease_types SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class DiseaseType extends CompanyBase {

    private String code;
    private String description;
}
