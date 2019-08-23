package com.app.service.impl;

import com.app.domain.Course;
import com.app.exception.CourseNotFound;
import com.app.repos.CourseRepos;
import com.app.service.CurseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author Alvin
 **/
@Log4j2
@Service
@RequiredArgsConstructor
public class CurseServiceImpl implements CurseService {

    @NonNull
    private final CourseRepos courseRepos;

    @Override
    public Course findByDateAndId(Date date, Long id) {
        var val = courseRepos.findByDateAndId(date, id);
        if (Objects.isNull(val)) {
            log.warn("Course not found by: \nDate:" + date.toString() + "\n ID: " + id.toString());
            throw new CourseNotFound();
        }
        log.info("Find course: " + val.toString());
        return val;
    }

    @Override
    public Course save(Course course) {
        log.info("Currency course has been saved : " + course.toString());
        return courseRepos.save(course);
    }
}
