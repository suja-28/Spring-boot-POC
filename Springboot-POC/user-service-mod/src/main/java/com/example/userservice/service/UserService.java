package com.example.userservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.Response.Department;
import com.example.userservice.Response.ResponseTemplate;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
    	log.trace("trace");
    	log.debug("debug");
    	log.info("info");
    	log.warn("warn");
    	log.error("error");
        return userRepository.save(user);
    }

    public ResponseTemplate getUserWithDepartment(Long userId) {
        ResponseTemplate res = new ResponseTemplate();
        User user = userRepository.findByUserId(userId);

        Department department =
        		restTemplate.getForObject("http://localhost:8001/departments/" + user.getDepartmentId(),
        				Department.class);
        
        res.setUser(user);
        res.setDepartment(department);

        return  res;
    }
}