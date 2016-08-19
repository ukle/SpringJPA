package com.jpa.demo.controller;

import com.jpa.demo.po.User;
import com.jpa.demo.repository.UserDeptRepositoryImpl;
import com.jpa.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserDeptController {

    @Autowired
    private UserDeptRepositoryImpl userDeptRepository;

    @RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
    public List<UserVO> find(User user) {
        return userDeptRepository.findAll(user);
    }
}
