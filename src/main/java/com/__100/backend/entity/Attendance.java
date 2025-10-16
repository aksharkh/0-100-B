package com.__100.backend.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "attendance", uniqueConstraints = @UniqueConstraint(columnNames = {"employee-id", "date"}))
public class Attendance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate date;

    private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;

    private Long durationMinutes;

}
