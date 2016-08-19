package com.jpa.demo.repository;

import com.jpa.demo.po.Customer;
import com.jpa.demo.po.Order;
import com.jpa.demo.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
*
*@author  zhousd
*@date    2015/10/28
*@version latest
*/
public class CustomerOrderRepositoryImpl {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    public List<CustomerVO> findAll(final Customer customer) {

        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Predicate p1 = cb.like(root.get("lastName").as(String.class), "%"+customer.getLastName()+"%");
                Predicate p2 = cb.equal(root.get("id").as(Integer.class), customer.getId());
                Predicate p3 = cb.gt(root.get("age").as(Integer.class), customer.getAge());
                SetJoin<Customer, Order> depJoin = root.join(root.getModel().getSet("orders", Order.class), JoinType.LEFT);

                Predicate p4 = cb.equal(depJoin.get("orderName").as(String.class), "test");
                query.where(cb.and(cb.and(p3,cb.or(p1,p2)),p4));
                query.orderBy(cb.desc(root.get("id").as(Integer.class)));
                return query.getRestriction();
            }
        };

        List<Customer> list = customerOrderRepository.findAll(spec);
        List<CustomerVO> cList = new ArrayList<CustomerVO>();
        for(Customer c : list) {
            CustomerVO cus = new CustomerVO();
            cus.setId(c.getId());
            cus.setAge(c.getAge());
            cus.setLastName(c.getLastName());
            cList.add(cus);
        }
        return cList;
    }
}
