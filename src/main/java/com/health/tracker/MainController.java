package com.health.tracker;

// controllers handle http requests for the application

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    // ALARMS
    @Autowired
    private AlarmService alarmService;

    @Autowired
    private AlarmRepository alarmRepository;

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

    // ALARMS
    @GetMapping(path="/addAlarm")
    public String showAlarmForm(Model model) {
        model.addAttribute("alarm", new Alarm());

        return "add_alarm";
    }

    @PostMapping(path="/process_alarm")
    public String addAlarm(Alarm alarm) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = customUserDetails.getUserId();

        alarm.setUserId(userId);
        alarmRepository.save(alarm);
        return "home";
    }

    @RequestMapping(path="/allAlarm", method=RequestMethod.GET)
    private String showAlarm (Model model) {
        List<Alarm> alarms = alarmService.getAlarms();
        model.addAttribute("alarmList", alarms);
        return "alarm_list";
    }
}

// Postmapping handles post requests
// getmapping handles get requests
// requestmapping handles all http operations