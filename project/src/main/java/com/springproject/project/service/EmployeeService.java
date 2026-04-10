package com.springproject.project.service;

import com.springproject.project.Entity.Employee;
import com.springproject.project.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;  //creating reference to use it in service now we can call productrepository.save(),findAll() etc.

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) { //Dependecy injection ""Spring, please create ProductRepository object and give it to me — I don't want to create it myself"
        this.employeeRepository = employeeRepository;
    }

    //Create employee with validation
    //save a employee-- return single product
    public Employee saveEmployee(Employee employee){

        //validation1 :name should not be null or empty
        if(employee.getName()==null || employee.getName().trim().isEmpty()){
            throw new RuntimeException("Employee name cannot be empty");

        }

        //Validation 2: salary should be greater than 0
        if(employee.getSalary() <=0 ){
            throw new RuntimeException("Salary must be greater than 0");

        }
        return employeeRepository.save(employee);

    }
    //get all employee-no input return list of employees
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    //get one employee by id
    public Employee getEmployeeById(int id){
        return employeeRepository.findById(id).orElseThrow(()  -> new RuntimeException("Employee not found with id: "+id));// lambda expression means if empty do this on right exeption
    }


    //Delete Employee with check
    public void deleteEmployee(int id){


        //check id employee exits
        if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Cannot delete. Employee not found with id: "+ id);

        }
        employeeRepository.deleteById(id);
    }

}
