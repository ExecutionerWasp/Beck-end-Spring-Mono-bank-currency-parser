package com.app.service;

import com.app.domain.Course;
import lombok.NonNull;

import java.util.Date;

/**
 * @author Alvin
 **/

public interface CurseService {

    Course findByDateAndId(@NonNull Date date, @NonNull Long id);

    @NonNull
    Course save(@NonNull Course course);
}
