package com.__100.backend.service.serviceImp;

import com.__100.backend.dto.employee.AttendanceDto;
import com.__100.backend.entity.Attendance;
import com.__100.backend.entity.Employee;
import com.__100.backend.repository.AttendanceRepository;
import com.__100.backend.repository.EmployeeRepository;
import com.__100.backend.service.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


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

        } else {

        }
    }

    @Override
    public  AttendanceDto punchOut(Long employeeId){

    }

    @Override
    public List<AttendanceDto> getMonthlyAttendance(Long employeeId, int year, int month){

    }

    @Override
    public List<AttendanceDto> getAttendanceForRange(LocalDate start, LocalDate end){

    }


}
