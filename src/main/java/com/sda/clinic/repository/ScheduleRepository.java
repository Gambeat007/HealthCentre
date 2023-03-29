package com.sda.clinic.repository;

import com.sda.clinic.models.company.doctor.DoctorCalendar;
import com.sda.clinic.models.company.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<DoctorCalendar, Long> {

    @Query("from DoctorCalendar dc where dc.user = :user and dc.startTime >= :dateFrom and dc.endTime <= :dateTo")
    Set<DoctorCalendar> findByDateRange(@Param("dateFrom") LocalDateTime dateFrom,
                                        @Param("dateTo") LocalDateTime dateTo,
                                        @Param("user") User user);
    Optional<DoctorCalendar> findByUuid(@Param("uuid") UUID uuid);

}
