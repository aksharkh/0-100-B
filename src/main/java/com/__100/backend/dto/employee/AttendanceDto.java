package com.__100.backend.dto.employee;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceDto {
    private Long id;
    private String employeeId;
    private String employeeCode;
    private LocalDate date;
    private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;
    private Long durationTime;
}
