package com.jpa.demo.repository;


import com.jpa.demo.po.CourseTypePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CourseTypeRepository extends JpaRepository<CourseTypePO, Integer>, JpaSpecificationExecutor<CourseTypePO> {

    //通过解析方法名创建查询
    public CourseTypePO findById(Integer Id);
    public List<CourseTypePO> findByTitleAndProjectId(String Title, Integer ProjectId);
    public List<CourseTypePO> findByTitleOrProjectId(String Title, Integer ProjectId);
    public List<CourseTypePO> findBySortNumberBetween(Integer from, Integer to);
    public List<CourseTypePO> findBySortNumberLessThan(Integer from);
    public List<CourseTypePO> findBySortNumberGreaterThan(Integer from);
    public List<CourseTypePO> findByProjectIdOrderBySortNumberDesc(Integer ProjectId);

    //使用 @Query 创建查询
    @Query("SELECT a FROM CourseTypePO a WHERE a.projectId=?1")
    public Page<CourseTypePO> findByProjectId(Integer projectId, Pageable page);

    @Query("SELECT a FROM CourseTypePO a")
    public Iterable<CourseTypePO> findByProjectIdBySort(CourseTypePO courseTypeVO, Sort sort);

    @Modifying//支持更新类的Query语句，添加@Modifying,还要有事务支持
    @Transactional(propagation = Propagation.REQUIRED)
    @Query(value="DELETE CourseTypePO u WHERE u.updateTime=?1")
    public void deleteByUpdateTime(String updateTime);

    @Query(value = "select DISTINCT u.id FROM CourseTypePO u WHERE u.title LIKE %?1%")
    public List<Integer> findByTitle(String title);
    @Query("SELECT new CourseTypePO(a.projectId, a.title) FROM CourseTypePO a WHERE a.id>?1")
    public List<CourseTypePO> findSomething(Integer id);
    @Query("SELECT a FROM CourseTypePO a WHERE a.content=:con")
    public List<CourseTypePO> findByContent(@Param("con") String content);

    //动态构建查询
    public List<CourseTypePO> findAll(Specification<CourseTypePO> spec);
    public Page<CourseTypePO> findAll(Specification<CourseTypePO> spec, Pageable pageable);

    //自定义
    public Page<CourseTypePO> getByCondition(String title);

}
