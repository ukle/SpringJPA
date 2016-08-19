package com.jpa.demo.controller;

import com.jpa.demo.po.Customer;
import com.jpa.demo.repository.CustomerOrderRepositoryImpl;
import com.jpa.demo.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerOrderController {

    @Autowired
    private CustomerOrderRepositoryImpl customerOrderRepository;

    @RequestMapping(value = "/customer", method = {RequestMethod.POST, RequestMethod.GET})
    public List<CustomerVO> find(Customer customer) {
        return customerOrderRepository.findAll(customer);
    }
}