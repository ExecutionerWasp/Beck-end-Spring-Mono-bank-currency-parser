package com.app.controller;

import com.app.domain.Course;
import com.app.service.CourseService;
import com.app.util.RequestType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alvin
 **/
@Log4j2
@RestController
@RequestMapping(value = "/course", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class CourseController {

    @NonNull
    private final CourseService courseService;

    /**
     * List of parsed json taken from mono bank
     * */
    @GetMapping
    public List<Course> findAll() {
        log.info("Finding courses ...");
        return courseService.findAllByHttpRequest(RequestType.MONO_BANK);
    }

    /**
     * Saving all mono bank courses
     * */
    @GetMapping("/save")
    public List<Course> saveAll() {
        this.findAll().forEach(courseService::save);
        return courseService.findAll();
    }
    /**
     * @param mnemonics - requestable mnemonics if need to request one or more once
     * @return course by request mnemonics
     * */
    @GetMapping("/get")
    public List<Course> getCourseByMnemonics(@RequestParam(name = "m") List<String> mnemonics) {
        log.info("Requested course by params : " + mnemonics);
        return mnemonics.stream()
                .map(String::toUpperCase)
                .map(courseService::findByMnemonics)
                .collect(Collectors.toList());
    }

    /**
     * @param code of currency takes by request param
     * Date is current
     * @return course by currency code
     * */
    @GetMapping("/getByCode")
    public Course getByCurrencyCode(@RequestParam(name = "c") Long code){
        return courseService.findByCurrencyCodeAndDate(code, new Date());
    }
}
