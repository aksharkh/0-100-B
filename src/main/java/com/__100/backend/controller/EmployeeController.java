package com.__100.backend.controller;


import com.__100.backend.dto.employee.EmployeeDto;
import com.__100.backend.dto.employee.EmployeeRequestDto;
import com.__100.backend.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeRequestDto req){
        return ResponseEntity.ok(employeeService.createEmployee(req));

    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        return ResponseEntity.ok(employeeService.getAll(PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id){
        return  ResponseEntity.ok(employeeService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @RequestBody EmployeeRequestDto req){
        return ResponseEntity.ok(employeeService.updateEmployee(id, req));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Deleted");
    }
}
