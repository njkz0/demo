package com.example.demo.controller;

import com.example.demo.model.Cleaner;
import com.example.demo.model.CupOfCoffee;
import com.example.demo.service.CleanerService;
import com.example.demo.service.CupOfCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coffee_machine")
@RequiredArgsConstructor
public class CoffeeMachineController {

    private final CupOfCoffeeService cupOfCoffeeService;
    private final CleanerService cleanerService;

    @GetMapping("/{name}")
    public ResponseEntity<CupOfCoffee> doCoffee(@PathVariable String name) {
        try {
            CupOfCoffee cupOfCoffee = cupOfCoffeeService.doCupOfCoffeeByName(name);
            cleanerService.plusPreparation();
            return new ResponseEntity<>(cupOfCoffee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create_new_coffee")
    public ResponseEntity<CupOfCoffee> createNewCoffee(@RequestBody CupOfCoffee cupOfCoffee) {
        try {
            CupOfCoffee newCupOfCoffee = cupOfCoffeeService.saveCupOfCoffee(cupOfCoffee);
            return new ResponseEntity<>(newCupOfCoffee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_my_coffee")
    public ResponseEntity<CupOfCoffee> updateMyCoffee(@RequestBody CupOfCoffee cupOfCoffee) {
        try {
            CupOfCoffee updatedCup = cupOfCoffeeService.updateCupOfCoffee(cupOfCoffee);
            return new ResponseEntity<>(updatedCup, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CupOfCoffee> deleteCoffee(@PathVariable Integer id){
        cupOfCoffeeService.deleteMyCupOfCoffeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/clean_machine")
    public ResponseEntity<Cleaner> cleanMachine(){
        Cleaner cleaner = cleanerService.cleanMachine();
        return new ResponseEntity<>(cleaner, HttpStatus.OK);
    }
}
