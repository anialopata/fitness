package com.anialopata.repository;

import com.anialopata.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ania on 2018-07-29.
 */
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
