package com.app.controller;

import com.app.domain.Course;
import com.app.service.CourseService;
import com.app.util.RequestType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alvin
 **/
@Log4j2
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    @NonNull
    private final CourseService courseService;

    @GetMapping
    public List<Course> findAll() {
        log.info("Finding courses ...");
        return courseService.findAllByHttpRequest(RequestType.MONO_BANK);
    }

    @GetMapping("/save")
    public List<Course> saveAll() {
        this.findAll().forEach(courseService::save);
        return courseService.findAll();
    }

    @GetMapping("/get")
    public List<Course> getCourseByMnemonics(@RequestParam(name = "m") List<String> mnemonics) {
        log.info("Requested course by params : " + mnemonics);
        return mnemonics.stream()
                .map(String::toUpperCase)
                .map(courseService::findByMnemonics)
                .collect(Collectors.toList());
    }

    @GetMapping("/getByCode")
    public Course getByCurrencyCode(@RequestParam(name = "c") Long code){
        return courseService.findByCurrencyCodeAndDate(code, new Date());
    }
}
