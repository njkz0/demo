package com.coffee.demo.service.impl;

import com.coffee.demo.service.CupOfCoffeeService;
import com.coffee.demo.dao.CupOfCoffeeDAO;
import com.coffee.demo.model.CupOfCoffee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CupOfCoffeeServiceImpl  implements CupOfCoffeeService {

    private final CupOfCoffeeDAO cupOfCoffeeDAO;

    @Override
    public CupOfCoffee doCupOfCoffeeByName(String name) {
        CupOfCoffee cupOfCoffee= cupOfCoffeeDAO.findByName(name);
        if(cupOfCoffee != null){
            return cupOfCoffee;
        }
        throw new RuntimeException("Cant find coffee");

    }

    @Override
    public CupOfCoffee saveCupOfCoffee(CupOfCoffee cupOfCoffee) {
        if (cupOfCoffee.getId() == null && cupOfCoffeeDAO.findByName(cupOfCoffee.getName()) == null){
           return cupOfCoffeeDAO.save(cupOfCoffee);
        }
        throw new RuntimeException("Cant save coffee");
    }

    @Override
    public CupOfCoffee updateCupOfCoffee(CupOfCoffee cupOfCoffee) {
        if (cupOfCoffee.getId() != null && cupOfCoffeeDAO.findByName(cupOfCoffee.getName()) != null)
       return cupOfCoffeeDAO.save(cupOfCoffee);
    throw new RuntimeException("Cant Update coffee");
    }

    @Override
    public void deleteMyCupOfCoffeeById(Integer id) {
      cupOfCoffeeDAO.deleteById(id);
    }

    @Override
    public List<CupOfCoffee> getAllCoffee() {
        return cupOfCoffeeDAO.findAllExistedCoffee();
    }
}
