package com.example.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/departments")
@RefreshScope
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    
    @Value("${welcome.message: Default hello}")
    private String msg;
    
    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
    	Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
                return departmentService.findDepartmentById(departmentId);
    }

    @GetMapping("/msg")
    public String getMsg() {
    	return msg;
    }
}
