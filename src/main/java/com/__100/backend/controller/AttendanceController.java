package com.__100.backend.controller;


import com.__100.backend.dto.employee.AttendanceDto;
import com.__100.backend.service.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;


    @PostMapping("/punch-in/{employeeId}")
    public ResponseEntity<AttendanceDto> punchIn(@PathVariable Long employeeId){
        return ResponseEntity.ok(attendanceService.punchIn(employeeId));
    }


    @PostMapping("/punch-out/{employeeId")
    public ResponseEntity<AttendanceDto> punchOut(@PathVariable Long employeeId){
        return ResponseEntity.ok(attendanceService.punchOut(employeeId));
    }

    @GetMapping("/employee/{employeeId}/month")
    public ResponseEntity<List<AttendanceDto>> getMonthly(@PathVariable Long employeeId, @RequestParam int year, @RequestParam int month){
        return ResponseEntity.ok(attendanceService.getMonthlyAttendance(employeeId, year, month));
    }

    @GetMapping("/range")
    public ResponseEntity<List<AttendanceDto>> getRange(@RequestParam String start, @RequestParam String end){
        LocalDate s = LocalDate.parse(start);
        LocalDate e = LocalDate.parse(end);
        return ResponseEntity.ok(attendanceService.getAttendanceForRange(s, e));
    }


}
