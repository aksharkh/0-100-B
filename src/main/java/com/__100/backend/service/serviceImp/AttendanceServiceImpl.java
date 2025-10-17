package com.__100.backend.service.serviceImp;

import com.__100.backend.dto.UserDto;
import com.__100.backend.dto.employee.AttendanceDto;
import com.__100.backend.entity.Attendance;
import com.__100.backend.entity.Employee;
import com.__100.backend.entity.User;
import com.__100.backend.repository.AttendanceRepository;
import com.__100.backend.repository.EmployeeRepository;
import com.__100.backend.service.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public AttendanceDto punchIn(Long employeeId){
        Employee e = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeAndDate(e, today).orElse(null);


        if(attendance == null) {
            Attendance a = new Attendance();
            a.setEmployee(e);
            a.setDate(today);
            a.setPunchInTime(LocalDateTime.now());
            Attendance saved = attendanceRepository.save(a);
            return toDto(saved);
        } else {
            if(attendance.getPunchInTime() == null){
                attendance.setPunchInTime(LocalDateTime.now());
                Attendance saved = attendanceRepository.save(attendance);
                return toDto(saved);
            } else {
                throw new RuntimeException("Already punched in for today");
            }
        }
    }

    @Override
    public  AttendanceDto punchOut(Long employeeId){
        Employee e = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByEmployeeAndDate(e, today).orElseThrow(() -> new RuntimeException("Punch-in record not found"));

        if(attendance.getPunchOutTime() != null) {
            throw  new RuntimeException("Already punched out for today");
        }

        attendance.setPunchOutTime(LocalDateTime.now());
        if(attendance.getPunchOutTime() != null){
            Duration duration = Duration.between(attendance.getPunchInTime(), attendance.getPunchOutTime());
            attendance.setDurationMinutes(duration.toMinutes());
        }

        Attendance saved = attendanceRepository.save(attendance);
        return toDto(saved);
    }

    @Override
    public List<AttendanceDto> getMonthlyAttendance(Long employeeId, int year, int month){
        Employee e = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();
        List<Attendance> list = attendanceRepository.findByEmployeeAndDateBetween(e,start, end);
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDto> getAttendanceForRange(LocalDate start, LocalDate end){
        List<Attendance> list = attendanceRepository.findByDateBetween(start, end);
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    private AttendanceDto toDto(Attendance a){
        AttendanceDto dto = new AttendanceDto();
        dto.setId(a.getId());
        dto.setEmployeeId(a.getEmployee().getId());
        dto.setEmployeeCode(a.getEmployee().getEmployeeCode());
        dto.setDate(a.getDate());
        dto.setPunchInTime(a.getPunchInTime());
        dto.setPunchOutTime(a.getPunchOutTime());
        dto.setDurationTime(a.getDurationMinutes());

        return dto;

    }


}
