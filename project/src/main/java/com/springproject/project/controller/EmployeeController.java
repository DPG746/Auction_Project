package com.springproject.project.controller;

import com.springproject.project.Entity.Employee;
import com.springproject.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    //Get a product by ID
    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    // get a product by id

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    //Delete a product by ID
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }


}
