package com.__100.backend.service.service;

import com.__100.backend.dto.employee.AttendanceDto;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    AttendanceDto punchIn(Long employeeId);
    AttendanceDto punchOut(Long employeeId);
    List<AttendanceDto> getMonthlyAttendance(Long employeeId, int year, int month);
    List<AttendanceDto> getAttendanceForRange(LocalDate start, LocalDate end);
}
