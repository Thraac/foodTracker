package com.health.tracker;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Integer> {
    List<Food> findByUserId(Long userId);
}
