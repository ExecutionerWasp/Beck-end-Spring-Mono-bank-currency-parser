package com.app.repos;

import com.app.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Alvin
 **/
@Repository
public interface CourseRepos extends CrudRepository<Course, Long> {

    Course findByDateAndId(Date date, Long id);
}
