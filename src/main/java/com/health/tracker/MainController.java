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
@RequestMapping(path="/demo") // maps requests for urls that start with /demo, after application path
public class MainController {
    @Autowired // this gets the bean called userRepository
    private UserRepository userRepository;

    // @PostMapping(path="/add") // mapp only post requests
    // // ResponseBody returned string is the response not a view
    // // RequestParam means it is a parameter from the GET or POST request
    // public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
    //     User newUser = new User();
    //     newUser.setName(name);
    //     newUser.setEmail(email);
    //     userRepository.save(newUser);
    //     return "Saved";
    // }

    @GetMapping(path="/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";

    }

    @PostMapping(path="/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "register_success";
    }

    @GetMapping(path="/all") // map only get requests
    public @ResponseBody Iterable<User> getAllUsers() {
        // this returns a json or xml with the users
        return userRepository.findAll();
    }
}

// Postmapping handles post requests
// getmapping handles get requests
// requestmapping handles all http operations