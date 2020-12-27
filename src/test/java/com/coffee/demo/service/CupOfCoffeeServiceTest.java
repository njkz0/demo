package com.coffee.demo.service;

import com.coffee.demo.dao.CupOfCoffeeDAO;
import com.coffee.demo.model.CupOfCoffee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CupOfCoffeeServiceTest {

    @Autowired
    CupOfCoffeeService cupOfCoffeeService;

    @MockBean
    CupOfCoffeeDAO cupOfCoffeeDAO;

    CupOfCoffee returnCupOfCoffee;
    List<CupOfCoffee> cupOfCoffeeList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        returnCupOfCoffee = new CupOfCoffee();
        returnCupOfCoffee.setName("cappuccino");

        CupOfCoffee cupOfCoffeeForList1 = new CupOfCoffee();
        cupOfCoffeeForList1.setName("cappuccino");
        cupOfCoffeeList.add(cupOfCoffeeForList1);
        CupOfCoffee cupOfCoffeeForList2 = new CupOfCoffee();
        cupOfCoffeeForList1.setName("espresso");
        cupOfCoffeeList.add(cupOfCoffeeForList2);

    }


    @Test
    void doCupOfCoffeeByName() {
        when(cupOfCoffeeDAO.findByName(any(String.class))).thenReturn(returnCupOfCoffee);

        String name = "cappuccino";
        CupOfCoffee result = cupOfCoffeeService.doCupOfCoffeeByName(name);

        assertEquals(name, result.getName());
        verify(cupOfCoffeeDAO).findByName(any(String.class));


    }

    @Test
    void saveCupOfCoffee() {
        CupOfCoffee savedCupOfCoffee  = new CupOfCoffee ();
        savedCupOfCoffee.setName("cappuccino");

        when(cupOfCoffeeDAO.findByName(anyString())).thenReturn(null);
        when(cupOfCoffeeDAO.save(any(CupOfCoffee.class))).thenReturn(returnCupOfCoffee);

        CupOfCoffee result = cupOfCoffeeService.saveCupOfCoffee(savedCupOfCoffee);

        assertEquals(returnCupOfCoffee.getName(), result.getName());
        verify(cupOfCoffeeDAO).findByName(anyString());
        verify(cupOfCoffeeDAO).save(any(CupOfCoffee.class));


    }

    @Test
    void updateCupOfCoffee() {
        returnCupOfCoffee.setId(1);

        CupOfCoffee updateCupOfCoffee  = new CupOfCoffee ();
        updateCupOfCoffee.setName("espresso");
        updateCupOfCoffee.setId(1);

        when(cupOfCoffeeDAO.findByName(anyString())).thenReturn(returnCupOfCoffee);
        when(cupOfCoffeeDAO.save(any(CupOfCoffee.class))).thenReturn(updateCupOfCoffee);

        CupOfCoffee result = cupOfCoffeeService.updateCupOfCoffee(updateCupOfCoffee);

        assertEquals(updateCupOfCoffee.getName(), result.getName());
        verify(cupOfCoffeeDAO).findByName(anyString());
        verify(cupOfCoffeeDAO).save(any(CupOfCoffee.class));


    }

    @Test
    void getAllNamesOfCoffee() {
        when(cupOfCoffeeDAO.findAllExistedCoffee()).thenReturn(cupOfCoffeeList);

        List <CupOfCoffee> result = cupOfCoffeeService.getAllCoffee();

        assertEquals(cupOfCoffeeList.size(), result.size());
        verify(cupOfCoffeeDAO).findAllExistedCoffee();
    }


}