package com.app.service;

import com.app.domain.Course;
import com.app.domain.Currency;
import com.app.domain.Mnemonics;
import com.app.util.RequestType;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Alvin
 **/

public interface CourseService {

    Course findByMnemonics(@NotNull String m);

    default Course findByMnemonics(@NotNull Mnemonics m){
        return this.findByMnemonics(m.getMnemonic());
    }

    Course findByCurrencyA(@NotNull Currency currencyA);

    Course findByCurrencyCodeAndDate(@NonNull Long code, @NonNull Date date);

    @NonNull
    Course save(@NonNull Course course);

    @NonNull
    List<Course> findAllByHttpRequest(@NonNull RequestType type);

    List<Course> findAll();
}
