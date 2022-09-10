package com.example.SearchDB.controllers;

import com.example.SearchDB.models.Car;
import com.example.SearchDB.models.Post;
import com.example.SearchDB.models.User;
import com.example.SearchDB.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public String userAdd(User user, Model model) { return "user-add";}

//    @PostMapping("/user/add")
//    public String userNewAdd(@RequestParam String firstName,
//                             @RequestParam String secondName,
//                             @RequestParam String middleName,
//                             @RequestParam String birthday,
//                             @RequestParam int passportSeries,
//                             @RequestParam int passportNumber, Model model)
//    {
//        User user = new User(firstName, secondName, middleName, birthday, passportSeries, passportNumber);
//        userRepository.save(user);
//        return "redirect:/user";
//    }

    @PostMapping("/user/add")
    public String userNewAdd(@ModelAttribute("user") @Valid User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "user-add";
        }
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

    @GetMapping("/user/{id}")
    public String userDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        if(!userRepository.existsById(id))
        {
            return "redirect:/user";
        }
        return "user-details";
    }

//    @GetMapping("/user/{id}/edit")
//    public String userEdit(@PathVariable("id")long id,
//                          Model model)
//    {
//        if(!userRepository.existsById(id)){
//            return "redirect:/user";
//        }
//        Optional<User> user = userRepository.findById(id);
//        ArrayList<User> res = new ArrayList<>();
//        user.ifPresent(res::add);
//        model.addAttribute("user",res);
//        return "user-edit";
//    }

    @GetMapping("/user/{id}/edit")
    public String userEdit(@PathVariable("id")long id, Model model)
    {
        User res = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("user",res);
        return "user-edit";
    }

//    @PostMapping("/user/{id}/edit")
//    public String userUpdate(@PathVariable("id")long id,
//                             @RequestParam String firstName,
//                             @RequestParam String secondName,
//                             @RequestParam String middleName,
//                             @RequestParam String birthday,
//                             @RequestParam int passportSeries,
//                             @RequestParam int passportNumber,
//                            Model model)
//    {
//        User user = userRepository.findById(id).orElseThrow();
//        user.setFirstName(firstName);
//        user.setSecondName(secondName);
//        user.setMiddleName(middleName);
//        user.setBirthday(birthday);
//        user.setPassportSeries(passportSeries);
//        user.setPassportNumber(passportNumber);
//        userRepository.save(user);
//        return "redirect:/user";
//    }

    @PostMapping("/user/{id}/edit")
    public String userUpdate(@PathVariable("id")long id, @ModelAttribute("user")@Valid User user, BindingResult bindingResult)
    {
        user.setId(id);
        if(bindingResult.hasErrors())
        {
            return "user-edit";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @PostMapping("/user/{id}/remove")
    public String userDelete(@PathVariable("id") long id, Model model){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/user";
    }
}
