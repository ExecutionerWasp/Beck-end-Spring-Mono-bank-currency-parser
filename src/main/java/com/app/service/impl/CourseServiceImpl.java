package com.app.service.impl;

import com.app.domain.Course;
import com.app.domain.Currency;
import com.app.domain.Mnemonics;
import com.app.exception.CurrencyNotFoundException;
import com.app.repos.CourseRepos;
import com.app.service.CourseService;
import com.app.service.CurrencyService;
import com.app.util.RequestType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alvin
 **/
@Log4j2
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    @NonNull
    private final CourseRepos courseRepos;

    @NonNull
    private final CurrencyService currencyService;

    @Override
    @Cacheable("courses")
    public Course findByMnemonics(@NotNull String m) {
        return this.findByCurrencyA(currencyService.findByMnemonics(m));
    }

    @Override
    @Cacheable("courses")
    public Course findByCurrencyA(@NotNull Currency currencyA) {
        log.info("Searching by : " + currencyA);
        return courseRepos.findByCurrencyA(currencyA);
    }

    @Override
    @Cacheable("courses")
    public Course findByCurrencyCodeAndDate(@NonNull Long code, @NonNull Date date) {
        log.info("Finding dy currency code and date");
        Currency currency = null;
        try {
            currency = currencyService.findById(code);
        } catch (CurrencyNotFoundException e) {
            log.info("Currency not found");
        }
        return courseRepos.findByCurrencyAAndDate(currency, date);
    }

    @Override
    public Course save(Course course) {
        log.info("Currency course has been saved : " + course.toString());
        return courseRepos.save(course);
    }

    @Override
    @Cacheable("courses")
    public List<Course> findAllByHttpRequest(RequestType type) {
        JsonParser springParser = JsonParserFactory.getJsonParser();
        List<Object> list = springParser.parseList(type.getJson());

        List<Course> courses = new ArrayList<>();

        for (Object o : list) {
            Course course = null;
            if (o instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) o;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (entry.getKey().equals("currencyCodeA")){
                        try {
                            course = new Course();
                            course.setCurrencyA(currencyService.findById(entry.getValue().toString()));
                        } catch (CurrencyNotFoundException e) {
                            log.warn("Currency not found by id : " + entry.getKey());
                            course = null;
                            break;
                        }
                    }
                    if (entry.getKey().equals("currencyCodeB")){
                        try {
                            course.setCurrencyB(currencyService.findById(entry.getValue().toString()));
                        } catch (CurrencyNotFoundException e) {
                            log.warn("Currency not found by id : " + entry.getKey());
                            course = null;
                            break;
                        }
                    }
                    switch (entry.getKey()) {
                        case "date":
                            course.setDate(new Date(Long.valueOf(entry.getValue().toString()) * 1000));
                            break;
                        case "rateBuy":
                            course.setBuy(Double.valueOf(entry.getValue().toString()));
                            break;
                        case "rateSell":
                            course.setCell(Double.valueOf(entry.getValue().toString()));
                            break;
                    }
                }

            }
            if (Objects.nonNull(course)){
                courses.add(course);
            }
        }
        log.info("Mono bank course items : " + courses);
        log.info("Currency list size : " + courses.size());
        log.info("Currency data of mono bank has been get successfully");

        List<Course> filtered = courses.stream()
                .filter(course ->
                        course.getCurrencyB().equals(
                                currencyService.findByMnemonics(Mnemonics.UAH)))
                .collect(Collectors.toList());

        log.info("Filtered courses in relation to the mnemonics of the UAH");

        return filtered;
    }

    @Override
    @Cacheable("courses")
    public List<Course> findAll() {
        log.info("Courses has been saved");
        return courseRepos.findAll();
    }
}
