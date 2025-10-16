package com.__100.backend.dto.employee;


import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequestDto {
    private String firstName;
    private String lastName;
    private String employeeCode;
    private String email;
    private String designation;
    private LocalDate joiningDate;
}
