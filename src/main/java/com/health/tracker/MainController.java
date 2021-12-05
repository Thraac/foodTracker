package com.health.tracker;

// controllers handle http requests for the application

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // this marks the class as a controller
@RequestMapping(path="") // maps requests for urls that start with /demo, after application path
public class MainController {
    
    @Autowired // this gets the bean called userRepository
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

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

    @GetMapping(path="/all") // map only get requests
    public @ResponseBody Iterable<User> getAllUsers() {
        // this returns a json or xml with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/addFood")
    public String showFoodForm(Model model) {
        model.addAttribute("food", new Food());

        return "add_food";
    }

    @PostMapping(path="/process_food")
    public String addFood(Food food) {
        foodRepository.save(food);

        return "addFood";
    }
}

// Postmapping handles post requests
// getmapping handles get requests
// requestmapping handles all http operations