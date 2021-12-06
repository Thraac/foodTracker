package com.health.tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {
    @Autowired
    private AlarmRepository alarmRepository;

    public List<Alarm> getAlarms() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = customUserDetails.getUserId();

        List<Alarm> list = new ArrayList<>();
        alarmRepository.findByUserId(userId).forEach(list::add);
        return list;
    }
}
