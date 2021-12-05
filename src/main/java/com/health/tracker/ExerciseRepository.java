package com.health.tracker;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
    List<Exercise> findByUserId(Long userId);
}
