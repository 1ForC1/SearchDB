package com.example.SearchDB.controllers;

import com.example.SearchDB.models.Post;
import com.example.SearchDB.models.User;
import com.example.SearchDB.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String userMain(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user-main";
    }

    @GetMapping("/user/add")
    public String userAdd(Model model) { return "user-add";}

    @PostMapping("/user/add")
    public String userNewAdd(@RequestParam String firstName,
                             @RequestParam String secondName,
                             @RequestParam String middleName,
                             @RequestParam String birthday,
                             @RequestParam int age,
                             @RequestParam int passportSeries,
                             @RequestParam int passportNumber, Model model)
    {
        User user = new User(firstName, secondName, middleName, birthday, age, passportSeries, passportNumber);
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/user/filter")
    public String userFilter(Model model){ return "user-filter"; }

    @PostMapping("/user/filter/result")
    public String userResult(@RequestParam String secondName, Model model)
    {
        List<User> result = userRepository.findBySecondNameContains(secondName);
        model.addAttribute("result", result);
        return "user-filter";
    }
}
