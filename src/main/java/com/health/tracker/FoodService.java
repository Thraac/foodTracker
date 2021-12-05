package com.health.tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getFoods() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = customUserDetails.getUserId();

        List<Food> list = new ArrayList<>();
        // foodRepository.findAll().forEach(list::add);
        foodRepository.findByUserId(userId).forEach(list::add);
        return list;
    }
}
