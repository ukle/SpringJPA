package com.jpa.demo.repository;

import com.jpa.demo.po.CourseTypePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
*
*@author  zhousd
*@date    2015/10/28
*@version latest
*/
public class CourseTypeRepositoryImpl {

    @PersistenceContext
    private EntityManager em;
    public Page<CourseTypePO> getByCondition(String title){
        String hql = "select o from CourseTypePO o where o.title=:title";
        Query q = em.createQuery(hql);
        q.setParameter("title", title);
        Page<CourseTypePO> page = new PageImpl<CourseTypePO>(q.getResultList(),new PageRequest(0,1),3);
        return page;
    }
}
