package com.jpa.demo.controller;

import com.jpa.demo.po.CourseTypePO;
import com.jpa.demo.repository.CourseTypeRepository;
import com.jpa.demo.service.CourseTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseTypeController {

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    @Autowired
    private CourseTypeService courseTypeService;

    @RequestMapping(value = "/course/find", method = {RequestMethod.POST, RequestMethod.GET})
    public CourseTypePO find(CourseTypePO courseTypeVO) {

        return courseTypeRepository.findById(courseTypeVO.getId());
    }

    @RequestMapping(value = "/course/findList", method = {RequestMethod.POST, RequestMethod.GET})
    public List<CourseTypePO> findList(CourseTypePO courseTypeVO, Integer from, Integer to) {
        return courseTypeRepository.findByTitleAndProjectId(courseTypeVO.getTitle(), courseTypeVO.getProjectId());
//        return courseTypeRepository.findByTitleOrProjectId(courseTypeVO.getTitle(), courseTypeVO.getProjectId());
//        return courseTypeRepository.findBySortNumberBetween(from, to);
//        return courseTypeRepository.findByProjectIdOrderBySortNumberDesc(courseTypeVO.getProjectId());
//        return courseTypeRepository.findSomething(courseTypeVO.getId());
//        return courseTypeRepository.findByContent(courseTypeVO.getContent());
//        return courseTypeService.findAll(courseTypeVO);
//        return courseTypeService.findAllForPage(courseTypeVO, new PageRequest(0, 10));
    }

    @RequestMapping(value = "/course/findPage2", method = {RequestMethod.POST, RequestMethod.GET})
    public Iterable<CourseTypePO> findPage2(CourseTypePO courseTypeVO) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"projectId");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC,"sortNumber");
        orders.add(order);
        orders.add(order2);
        Sort sort = new Sort(orders);
        return courseTypeRepository.findByProjectIdBySort(courseTypeVO, sort);
//        return courseTypeRepository.getByCondition(courseTypeVO.getTitle());
    }

    @RequestMapping(value = "/course/findPage", method = {RequestMethod.POST, RequestMethod.GET})
    public Page<CourseTypePO> findPage(CourseTypePO courseTypeVO) {
        return courseTypeRepository.findByProjectId(courseTypeVO.getProjectId(), new PageRequest(0, 10));
    }

    @RequestMapping(value = "/course/save", method = {RequestMethod.POST, RequestMethod.GET})
    public CourseTypePO save(CourseTypePO courseTypeVO) {
        CourseTypePO courseTypePO = new CourseTypePO();
        BeanUtils.copyProperties(courseTypeVO, courseTypePO);
        return courseTypeRepository.saveAndFlush(courseTypePO);
    }

    @RequestMapping(value = "/course/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public String delete(CourseTypePO courseTypeVO) {
        courseTypeRepository.delete(courseTypeVO.getId());
//        courseTypeRepository.deleteByUpdateTime(courseTypeVO.getUpdateTime());
        return "success";
    }

    @RequestMapping(value = "/course/findint", method = {RequestMethod.POST, RequestMethod.GET})
    public List<Integer> findInt(CourseTypePO courseTypeVO) {
        return courseTypeRepository.findByTitle(courseTypeVO.getTitle());
    }

}
