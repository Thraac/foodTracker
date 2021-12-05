package com.health.tracker;

import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class FoodService {
    private FoodRepository foodRepository;

    public Iterable<Food> getFoods() {
        return foodRepository.findAll();
    }
}
