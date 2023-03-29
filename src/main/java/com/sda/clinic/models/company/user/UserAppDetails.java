package com.sda.clinic.models.company.user;

import com.sda.clinic.models.company.CompanyBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users_details")
@SQLDelete(sql = "UPDATE users_details SET status = 'DELETED' WHERE uuid = ?")
@Where(clause = "status = 'ACTIVE'")
public class UserAppDetails extends CompanyBase {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;
    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 50)
    @Column(name = "second_name")
    private String secondName;
    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 11)
    private String pesel;
    @Size(max = 20)
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "birth_day")
    private LocalDate birthDay;
    @Size(max = 10)
    private String nip;

}
