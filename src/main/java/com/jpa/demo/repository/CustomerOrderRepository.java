package com.jpa.demo.repository;

import com.jpa.demo.po.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
*@author  zhousd
*@date    2015/10/28
*@version latest
*/
@Repository
public interface CustomerOrderRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    List<Customer> findAll(Specification<Customer> spec);
}
