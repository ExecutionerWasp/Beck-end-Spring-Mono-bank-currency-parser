package com.app.service.impl;

import com.app.domain.Currency;
import com.app.domain.Course;
import com.app.repos.CourseRepos;
import com.app.repos.CurrencyRepos;
import com.app.service.CurrencyCurseService;
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
public class CurrencyCurseServiceImpl implements CurrencyCurseService {

    @NonNull
    private final CurrencyRepos currencyRepos;
    @NonNull
    private final CourseRepos courseRepos;

    @Override
    public boolean isExist(Currency currency) {
        return false;
    }

    @Override
    public Course findByDateAndId(Date date, Long id) {

        var val = courseRepos.findByDateAndId(date, id);

        if (Objects.isNull(val)) {

        }

        return null;
    }

    @Override
    public Course save(Course course) {
        log.info("Currency course has been saved : " + course.toString());
        return courseRepos.save(course);
    }
}
