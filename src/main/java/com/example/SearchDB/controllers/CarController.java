package com.example.SearchDB.controllers;

import com.example.SearchDB.models.Car;
import com.example.SearchDB.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String carAdd(Car car, Model model){
        return "car-add";
    }

//    @PostMapping("/car/add")
//    public String carNewAdd(@RequestParam String brand,
//                              @RequestParam String model,
//                              @RequestParam String body,
//                              @RequestParam String transmission,
//                              @RequestParam int power,
//                              @RequestParam int engineCapacity, Model model1) {
//        Car car = new Car(brand, model, body, transmission, power, engineCapacity);
//        carRepository.save(car);
//        return "redirect:/car";
//    }

    @PostMapping("/car/add")
    public String carNewAdd(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "car-add";
        }
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

    @GetMapping("/car/{id}")
    public String carDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Car> car = carRepository.findById(id);
        ArrayList<Car> res = new ArrayList<>();
        car.ifPresent(res::add);
        model.addAttribute("car", res);
        if(!carRepository.existsById(id))
        {
            return "redirect:/car";
        }
        return "car-details";
    }

//    @GetMapping("/car/{id}/edit")
//    public String carEdit(@PathVariable("id")long id,
//                           Model model)
//    {
//        if(!carRepository.existsById(id)){
//            return "redirect:/car";
//        }
//        Optional<Car> car = carRepository.findById(id);
//        ArrayList<Car> res = new ArrayList<>();
//        car.ifPresent(res::add);
//        model.addAttribute("car",res);
//        return "car-edit";
//    }

    @GetMapping("/car/{id}/edit")
    public String carEdit(@PathVariable("id")long id, Model model)
    {
        Car res = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("car",res);
        return "car-edit";
    }

//    @PostMapping("/car/{id}/edit")
//    public String carUpdate(@Valid Car car, @PathVariable("id")long id,
//                            @RequestParam String brand,
//                            @RequestParam String model,
//                            @RequestParam String body,
//                            @RequestParam String transmission,
//                            @RequestParam int power,
//                            @RequestParam int engineCapacity,
//                                 Model model1)
//    {
//        car = carRepository.findById(id).orElseThrow();
//        car.setBrand(brand);
//        car.setModel(model);
//        car.setBody(body);
//        car.setTransmission(transmission);
//        car.setPower(power);
//        car.setEngineCapacity(engineCapacity);
//        carRepository.save(car);
//        return "redirect:/car";
//    }

    @PostMapping("/car/{id}/edit")
    public String carUpdate(@PathVariable("id")long id, @ModelAttribute("car")@Valid Car car, BindingResult bindingResult)
    {
        car.setId(id);
        if(bindingResult.hasErrors())
        {
            return "car-edit";
        }
        carRepository.save(car);
        return "redirect:/car";
    }

    @PostMapping("/car/{id}/remove")
    public String carDelete(@PathVariable("id") long id, Model model){
        Car car = carRepository.findById(id).orElseThrow();
        carRepository.delete(car);
        return "redirect:/car";
    }
}
