package com.app.repos;

import com.app.domain.Course;
import com.app.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Alvin
 **/
@Repository
public interface CourseRepos extends JpaRepository<Course, Long> {

    Course findByCurrencyA(Currency currencyA);

    Course findByCurrencyAAndDate(Currency currencyA, Date date);
}
