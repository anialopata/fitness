package com.anialopata.repository;

import com.anialopata.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Ania on 2018-07-22.
 */
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByDate(LocalDateTime date);
    List<Exercise> findByDateIsBetween(LocalDateTime from, LocalDateTime to);

}
