package com.example.employeemanager.service;

import com.example.employeemanager.exception.UserNotFoundException;
import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;


    public Employee save(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString() + System.nanoTime());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow(() -> new UserNotFoundException("User by id + " + id + "was not found"));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

