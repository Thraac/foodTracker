package com.health.tracker;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AlarmRepository extends CrudRepository<Alarm, Integer> {
    List<Alarm> findByUserId(Long userId);
}
