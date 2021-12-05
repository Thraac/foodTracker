package com.health.tracker;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.health.tracker.Food;

public interface FoodRepository extends CrudRepository<Food, Integer> {
    List<Food> findByUserId(Long userId);
}
