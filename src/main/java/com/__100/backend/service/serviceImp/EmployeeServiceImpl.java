package com.__100.backend.service.serviceImp;

import com.__100.backend.dto.employee.EmployeeDto;
import com.__100.backend.dto.employee.EmployeeRequestDto;
import com.__100.backend.entity.Employee;
import com.__100.backend.repository.EmployeeRepository;
import com.__100.backend.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    @Override
    public EmployeeDto createEmployee(EmployeeRequestDto req){
        Employee e = modelMapper.map(req, Employee.class);
        Employee saved = employeeRepository.save(e);
        return modelMapper.map(saved, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeRequestDto req){
        Employee e = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        modelMapper.map(req, e);
        Employee updated = employeeRepository.save(e);
        return modelMapper.map(updated, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getById(Long id){
       Employee e = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        return modelMapper.map(e, EmployeeDto.class);
    }

    @Override
    public Page<EmployeeDto> getAll(Pageable pageable){
        Page<Employee> page = employeeRepository.findAll(pageable);
        return page.map(emp -> modelMapper.map(emp, EmployeeDto.class));
    }

    @Override
    public void deleteEmployee(Long id){
        Employee e = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(e);

    }

}
