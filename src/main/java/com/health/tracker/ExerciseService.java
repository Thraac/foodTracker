package com.health.tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getExercises() {
        List<Exercise> list = new ArrayList<>();
        exerciseRepository.findAll().forEach(list::add);
        return list;
    }
}
