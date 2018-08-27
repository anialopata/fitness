package com.anialopata.repository;

import com.anialopata.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ania on 2018-08-01.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
