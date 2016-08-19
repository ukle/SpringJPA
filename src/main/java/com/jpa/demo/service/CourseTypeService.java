package com.jpa.demo.service;

import com.jpa.demo.po.CourseTypePO;
import com.jpa.demo.repository.CourseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseTypeService {

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    public List<CourseTypePO> findAll(final CourseTypePO courseTypeVO) {

        List<CourseTypePO> departmentPOList = courseTypeRepository.findAll(new Specification<CourseTypePO>() {
            @Override
            public Predicate toPredicate(Root<CourseTypePO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                Integer projectId = courseTypeVO.getProjectId();
                Integer id = courseTypeVO.getId();
                String title = courseTypeVO.getTitle();

                /**
                 * 连接查询条件, 不定参数，可以连接0..N个查询条件
                 * 构建简单的Predicate示例：
                 * Predicate p1=cb.like(root.get(“name”).as(String.class), “%”+uqm.getName()+“%”);
                 * Predicate p2=cb.equal(root.get("uuid").as(Integer.class), uqm.getUuid());
                 * Predicate p3=cb.gt(root.get("age").as(Integer.class), uqm.getAge());
                 * 构建组合的Predicate示例：
                 * Predicate p = cb.and(p3,cb.or(p1,p2));
                 */
                Predicate p1 = cb.equal(root.get("projectId").as(Integer.class), projectId);
                if (!StringUtils.isEmpty(id)) {
                    Predicate p2 = cb.equal(root.get("id").as(Integer.class), id);
                    p1 = cb.and(p1, p2);
                }
                if (!StringUtils.isEmpty(title)) {
                    Predicate p4 = cb.equal(root.get("title").as(String.class), title);
                    p1 = cb.and(p1, p4);
                }
                list.add(p1);
                query.orderBy(cb.asc(root.get("sortNumber")));

                Predicate[] p = new Predicate[list.size()];
                return cb.or(list.toArray(p));
            }
        });
        return departmentPOList;
    }

    public Page<CourseTypePO> findAllForPage(final CourseTypePO courseTypeVO, Pageable pageable) {

        Page<CourseTypePO> page = courseTypeRepository.findAll(new Specification<CourseTypePO>() {
            @Override
            public Predicate toPredicate(Root<CourseTypePO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                Integer projectId = courseTypeVO.getProjectId();
                Integer id = courseTypeVO.getId();
                String title = courseTypeVO.getTitle();

                Predicate p1 = cb.equal(root.get("projectId").as(Integer.class), projectId);
                if (!StringUtils.isEmpty(id)) {
                    Predicate p2 = cb.equal(root.get("id").as(Integer.class), id);
                    p1 = cb.and(p1, p2);
                }
                if (!StringUtils.isEmpty(title)) {
                    Predicate p4 = cb.equal(root.get("title").as(String.class), title);
                    p1 = cb.and(p1, p4);
                }
                list.add(p1);
                query.orderBy(cb.asc(root.get("sortNumber")));

                Predicate[] p = new Predicate[list.size()];
                return cb.or(list.toArray(p));
            }
        }, pageable);
        return page;
    }

}
