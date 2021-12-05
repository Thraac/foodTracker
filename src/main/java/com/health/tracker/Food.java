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


    // SETTERS GETTERS 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    } 

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    } 

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    } 

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    } 

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    } 

    public float getServingSize() {
        return servingSize;
    }

    public void setServingSize(float servingSize) {
        this.servingSize = servingSize;
    }

    public float getServings() {
        return servings;
    }

    public void setServings(float servings) {
        this.servings = servings;
    } 
}
