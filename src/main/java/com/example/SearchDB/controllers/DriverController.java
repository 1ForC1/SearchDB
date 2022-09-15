package com.example.SearchDB.controllers;

import com.example.SearchDB.models.Address;
import com.example.SearchDB.models.Driver;
import com.example.SearchDB.models.Firm;
import com.example.SearchDB.models.Pasport;
import com.example.SearchDB.repo.AddressRepository;
import com.example.SearchDB.repo.DriverRepository;
import com.example.SearchDB.repo.FirmRepository;
import com.example.SearchDB.repo.PasportRepository;
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
public class DriverController {
    @Autowired
    private PasportRepository pasportRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private FirmRepository firmRepository;

    @Autowired
    public AddressRepository addressRepository;

    @GetMapping("/driver")
    public String driverMain(Model model){
        Iterable<Driver> drivers = driverRepository.findAll();
        model.addAttribute("driver", drivers);
        return "driver-main";
    }

    @GetMapping("/driver/add")
    public String driverAdd(Driver driver, Model model) {
        Iterable<Address> addresses = addressRepository.findAll();
        model.addAttribute("address", addresses);
        Iterable<Pasport> pasport = pasportRepository.findAll();
        model.addAttribute("pasport", pasport);
        return "driver-add";
    }

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

    @PostMapping("/driver/add")
    public String driverNewAdd(@ModelAttribute("driver") @Valid Driver driver, @RequestParam String street, @RequestParam String number, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "driver-add";
        }
        Address address = addressRepository.findByStreet(street);
        Pasport pasport = pasportRepository.findByNumber(number);
        driver = new Driver(driver.getFirstName(), driver.getSecondName(), driver.getMiddleName(), driver.getBirthday(), address, pasport);
        driverRepository.save(driver);
        return "redirect:/driver";
    }

    @GetMapping("/driver/filter")
    public String driverFilter(Model model){ return "driver-filter"; }

    @PostMapping("/driver/filter/result")
    public String driverResult(@RequestParam String secondName, Model model)
    {
        List<Driver> result = driverRepository.findBySecondNameContains(secondName);
        model.addAttribute("result", result);
        return "driver-filter";
    }

    @GetMapping("/driver/{id}")
    public String driverDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Driver> driver = driverRepository.findById(id);
        ArrayList<Driver> res = new ArrayList<>();
        driver.ifPresent(res::add);
        model.addAttribute("driver", res);
        if(!driverRepository.existsById(id))
        {
            return "redirect:/driver";
        }
        return "driver-details";
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

    @GetMapping("/driver/{id}/edit")
    public String driverEdit(@PathVariable("id")long id, Model model)
    {
        Driver res = driverRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("driver",res);
        return "driver-edit";
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

    @PostMapping("/driver/{id}/edit")
    public String driverUpdate(@PathVariable("id")long id, @ModelAttribute("driver")@Valid Driver driver, BindingResult bindingResult)
    {
        driver.setId(id);
        if(bindingResult.hasErrors())
        {
            return "driver-edit";
        }
        driverRepository.save(driver);
        return "redirect:/driver";
    }

    @PostMapping("/driver/{id}/remove")
    public String driverDelete(@PathVariable("id") long id, Model model){
        Driver driver = driverRepository.findById(id).orElseThrow();
        driverRepository.delete(driver);
        return "redirect:/driver";
    }

    @GetMapping("/driver-firm")
    private String driverFirm(Model model){
        Iterable<Driver> drivers = driverRepository.findAll();
        model.addAttribute("drivers", drivers);
        Iterable<Firm> firms = firmRepository.findAll();
        model.addAttribute("firms", firms);

        return "driver-firm-main";
    }

    @PostMapping("/driver-firm")
    public String driverFirmAdd(@RequestParam String driver, @RequestParam String firm, Model model)
    {
        Driver driver1 = driverRepository.findBySecondName(driver);
        Firm firm1 = firmRepository.findByName(firm);
        driver1.getFirms().add(firm1);
        firm1.getDrivers().add(driver1);
        driverRepository.save(driver1);
        firmRepository.save(firm1);
        return "driver-firm-main";
    }
}
