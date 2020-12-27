package com.coffee.demo.service;

import com.coffee.demo.model.CupOfCoffee;

import java.util.List;

public interface CupOfCoffeeService {

    CupOfCoffee doCupOfCoffeeByName(String name);
    CupOfCoffee saveCupOfCoffee(CupOfCoffee cupOfCoffee);
    CupOfCoffee updateCupOfCoffee(CupOfCoffee cupOfCoffee);
    void deleteMyCupOfCoffeeById(Integer id);
    List<CupOfCoffee> getAllCoffee();

  // static boolean checkCup(CupOfCoffee cupOfCoffee){
  //     if (cupOfCoffee.getMlOfMilk() + cupOfCoffee.getMlOfWater()<= 500 && cupOfCoffee.getNumberOfCoffeeTeaspoons()<=3 &&cupOfCoffee.getNumberOfShugarTeaspoons() <=3){
  //         return true;
  //     } return false;
  // }


}
