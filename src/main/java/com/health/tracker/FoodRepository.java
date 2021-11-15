package com.health.tracker;

import org.springframework.data.repository.CrudRepository;

import com.health.tracker.Food;

public interface FoodRepository extends CrudRepository<Food, Integer> {
    
}
