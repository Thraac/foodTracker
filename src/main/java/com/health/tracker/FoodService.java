package com.health.tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    // public Iterable<Food> getFoods() {
    //     return foodRepository.findAll();
    // }

    public List<Food> getFoods() {
        List<Food> list = new ArrayList<>();
        foodRepository.findAll().forEach(list::add);
        return list;
    }

}
