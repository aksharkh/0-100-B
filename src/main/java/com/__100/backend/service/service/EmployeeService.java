package com.__100.backend.service.service;

import com.__100.backend.dto.employee.EmployeeDto;
import com.__100.backend.dto.employee.EmployeeRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeRequestDto req);
    EmployeeDto updateEmployee(Long id, EmployeeRequestDto req);
    EmployeeDto getById(Long id);
    Page<EmployeeDto> getAll(Pageable pagable);
    void deleteEmployee(Long id);

}
