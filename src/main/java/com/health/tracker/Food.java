package com.health.tracker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="food")
public class Food {

    // needs the id tag
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // this should connect it to the user id
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 64)
    private String meal;

    @Column(nullable = false, length = 10)
    private int calories;

    @Column(nullable = false, length = 10)
    private int carbs;

    @Column(nullable = false, length = 10)
    private int protein;

    @Column(nullable = false, length = 10)
    private int fat;

    @Column(name = "serving_size", length = 10)
    private float servingSize;

    @Column(nullable = false, length = 10)
    private float servings;
}
