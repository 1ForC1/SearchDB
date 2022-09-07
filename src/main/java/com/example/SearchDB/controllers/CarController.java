package com.example.SearchDB.controllers;

import com.example.SearchDB.models.Car;
import com.example.SearchDB.models.Post;
import com.example.SearchDB.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/car")
    public String carMain(Model model){
        Iterable<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "car-main";
    }

    @GetMapping("/car/add")
    public String carAdd(Model model){
        return "car-add";
    }

    @PostMapping("/car/add")
    public String carNewAdd(@RequestParam String brand,
                              @RequestParam String model,
                              @RequestParam String body,
                              @RequestParam String transmission,
                              @RequestParam int power,
                              @RequestParam int engineСapacity, Model model1) {
        Car car = new Car(brand, model, body, transmission, power, engineСapacity);
        carRepository.save(car);
        return "redirect:/car";
    }

    @GetMapping("/car/filter")
    public String carFilter(Model model){ return "car-filter"; }

    @PostMapping("/car/filter/result")
    public String carResult(@RequestParam String brand, Model model)
    {
        List<Car> result = carRepository.findByBrandContains(brand);
        model.addAttribute("result", result);
        return "car-filter";
    }
}
