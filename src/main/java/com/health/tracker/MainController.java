package com.health.tracker;

import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

// controllers handle http requests for the application

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller // this marks the class as a controller
@RequestMapping(path="") // maps requests for urls that start with /demo, after application path
public class MainController {
    
    // FOOD
    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodRepository foodRepository;

    // EXERCISE
    @Autowired 
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    // USER
    @Autowired // this gets the bean called userRepository
    private UserRepository userRepository;

    
    // USER
    @GetMapping(path="/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping(path="/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        // user.setEmail(user.getEmail());
        user.setPassword(encodedPassword);
  
        userRepository.save(user);

        return "register_success";
    }

    // @GetMapping(path="/all") // map only get requests
    // public @ResponseBody Iterable<User> getAllUsers() {
    //     // this returns a json or xml with the users
    //     return userRepository.findAll();
    // }

    // FOOD
    @GetMapping(path="/addFood")
    public String showFoodForm(Model model) {
        model.addAttribute("food", new Food());

        return "add_food";
    }

    @PostMapping(path="/process_food")
    public String addFood(Food food) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = customUserDetails.getUserId();

        food.setUserId(userId);
        foodRepository.save(food);

        return "home";
    }

    @RequestMapping(path="/allFood", method=RequestMethod.GET)
    private String showFood (Model model) {
        
        List<Food> foods = foodService.getFoods();
        model.addAttribute("foodList", foods);
        return "food_list";
    }

    // EXERCISE
    @GetMapping(path="/addExercise")
    public String showExerciseForm(Model model) {
        model.addAttribute("exercise", new Exercise());

        return "add_exercise";
    }

    @PostMapping(path="/process_exercise")
    public String addExercise(Exercise exercise) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = customUserDetails.getUserId();

        exercise.setUserId(userId);
        exerciseRepository.save(exercise);
        return "home";
    }

    @RequestMapping(path="/allExercise", method=RequestMethod.GET)
    private String showExercise (Model model) {
        List<Exercise> exercises = exerciseService.getExercises();
        model.addAttribute("exerciseList", exercises);
        return "exercise_list";
    }
}

// Postmapping handles post requests
// getmapping handles get requests
// requestmapping handles all http operations