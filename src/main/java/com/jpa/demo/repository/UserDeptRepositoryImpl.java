package com.jpa.demo.repository;

import com.jpa.demo.po.Dept;
import com.jpa.demo.po.User;
import com.jpa.demo.vo.UserVO;
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
public class UserDeptRepositoryImpl {

    @Autowired
    private UserDeptRepository userDeptRepository;

    public List<UserVO> findAll(final User user) {

        Specification<User> spec = new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Predicate p1 = cb.like(root.get("name").as(String.class), "%"+user.getName()+"%");
                Predicate p2 = cb.equal(root.get("id").as(Integer.class), user.getId());
                Predicate p3 = cb.gt(root.get("age").as(Integer.class), user.getAge());

                Join<User, Dept> depJoin = root.join(root.getModel().getSingularAttribute("dept", Dept.class),JoinType.LEFT);

                Predicate p4 = cb.equal(depJoin.get("name").as(String.class), "test");
                query.where(cb.and(cb.and(p3,cb.or(p1,p2)),p4));
                query.orderBy(cb.desc(root.get("id").as(Integer.class)));
                return query.getRestriction();
            }
        };

        List<User> list = userDeptRepository.findAll(spec);
        List<UserVO> cList = new ArrayList<UserVO>();
        for(User c : list) {
            UserVO cus = new UserVO();
            cus.setId(c.getId());
            cus.setAge(c.getAge());
            cus.setName(c.getName());
            cList.add(cus);
        }
        return cList;
    }
}
