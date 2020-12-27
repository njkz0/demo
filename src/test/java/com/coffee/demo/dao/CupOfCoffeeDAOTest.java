package com.coffee.demo.dao;

import com.coffee.demo.model.CupOfCoffee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CupOfCoffeeDAOTest {

    @Autowired
    CupOfCoffeeDAO cupOfCoffeeDAO;

    List<CupOfCoffee> cupOfCoffeeList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        CupOfCoffee cupOfCoffee1= new CupOfCoffee();
        cupOfCoffee1.setName("espresso");
        cupOfCoffeeList.add(cupOfCoffee1);

       CupOfCoffee cupOfCoffee2 = new CupOfCoffee();
        cupOfCoffee2.setName("cappuccino");
        cupOfCoffeeList.add(cupOfCoffee2);
    }

    @Test
    void findAllExistedCoffee() {
        cupOfCoffeeList.stream().forEach(cupOfCoffee -> cupOfCoffeeDAO.save(cupOfCoffee));
        List<CupOfCoffee> result = cupOfCoffeeDAO.findAllExistedCoffee();
        assertEquals(2, result.size());

    }
}