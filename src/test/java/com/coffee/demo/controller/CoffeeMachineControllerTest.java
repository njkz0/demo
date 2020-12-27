package com.coffee.demo.controller;

import com.coffee.demo.model.Cleaner;
import com.coffee.demo.model.CupOfCoffee;
import com.coffee.demo.service.CleanerService;
import com.coffee.demo.service.CupOfCoffeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoffeeMachineControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    CupOfCoffeeService cupOfCoffeeService;

    @MockBean
    CleanerService cleanerService;

    @Test
    void doCoffee() throws URISyntaxException {
        CupOfCoffee cupOfCoffee = new CupOfCoffee();
        cupOfCoffee.setName("test");

        Cleaner cleaner = new Cleaner();

        when(cupOfCoffeeService.doCupOfCoffeeByName(any(String.class))).thenReturn(cupOfCoffee);
        when(cleanerService.plusPreparation()).thenReturn(cleaner);

        RequestEntity<CupOfCoffee> requestEntity = new RequestEntity<>(cupOfCoffee, HttpMethod.PUT, new URI("/coffee_machine/test"));
        ResponseEntity<CupOfCoffee> responseEntity = testRestTemplate.exchange(requestEntity, CupOfCoffee.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(cupOfCoffeeService).doCupOfCoffeeByName(any(String.class));
        verify(cleanerService).plusPreparation();
    }

    @Test
    void doCoffeeWithBadRequest() throws URISyntaxException {
        CupOfCoffee cupOfCoffee = new CupOfCoffee();
        cupOfCoffee.setName("test");

        when(cupOfCoffeeService.doCupOfCoffeeByName(any(String.class))).thenReturn(cupOfCoffee);
        when(cleanerService.plusPreparation()).thenThrow(new RuntimeException());

        RequestEntity<CupOfCoffee> requestEntity = new RequestEntity<>(cupOfCoffee, HttpMethod.PUT, new URI("/coffee_machine/test"));
        ResponseEntity<CupOfCoffee> responseEntity = testRestTemplate.exchange(requestEntity, CupOfCoffee.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        verify(cupOfCoffeeService).doCupOfCoffeeByName(any(String.class));
        verify(cleanerService).plusPreparation();
    }

    @Test
    void createNewCoffee() throws URISyntaxException {
        CupOfCoffee cupOfCoffee = new CupOfCoffee();

        when(cupOfCoffeeService.saveCupOfCoffee(any(CupOfCoffee.class))).thenReturn(cupOfCoffee);

        RequestEntity<CupOfCoffee> requestEntity = new RequestEntity<>(cupOfCoffee, HttpMethod.POST, new URI("/coffee_machine"));
        ResponseEntity<CupOfCoffee> responseEntity = testRestTemplate.exchange(requestEntity, CupOfCoffee.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        verify(cupOfCoffeeService).saveCupOfCoffee(any(CupOfCoffee.class));
    }

    @Test
    void createWithBadRequest() throws URISyntaxException {

        CupOfCoffee cupOfCoffee = new CupOfCoffee();

        when(cupOfCoffeeService.saveCupOfCoffee(any(CupOfCoffee.class))).thenThrow(new RuntimeException());

        RequestEntity<CupOfCoffee> requestEntity = new RequestEntity<>(cupOfCoffee, HttpMethod.POST, new URI("/coffee_machine"));
        ResponseEntity<CupOfCoffee> responseEntity = testRestTemplate.exchange(requestEntity, CupOfCoffee.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

        verify(cupOfCoffeeService).saveCupOfCoffee(any(CupOfCoffee.class));


    }

    @Test
    void updateMyCoffee() throws URISyntaxException {
        CupOfCoffee cupOfCoffee = new CupOfCoffee();
        cupOfCoffee.setId(1);

        when(cupOfCoffeeService.updateCupOfCoffee(any(CupOfCoffee.class))).thenReturn(cupOfCoffee);

        RequestEntity<CupOfCoffee> requestEntity = new RequestEntity<>(cupOfCoffee, HttpMethod.PUT, new URI("/coffee_machine/update_my_coffee"));
        ResponseEntity<CupOfCoffee> responseEntity = testRestTemplate.exchange(requestEntity, CupOfCoffee.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(cupOfCoffeeService).updateCupOfCoffee(any(CupOfCoffee.class));
    }

    @Test
    void updateMyCoffeeWithBadRequest() throws URISyntaxException {
        CupOfCoffee cupOfCoffee = new CupOfCoffee();
        cupOfCoffee.setId(1);

        when(cupOfCoffeeService.updateCupOfCoffee(any(CupOfCoffee.class))).thenThrow(new RuntimeException());

        RequestEntity<CupOfCoffee> requestEntity = new RequestEntity<>(cupOfCoffee, HttpMethod.PUT, new URI("/coffee_machine/update_my_coffee"));
        ResponseEntity<CupOfCoffee> responseEntity = testRestTemplate.exchange(requestEntity, CupOfCoffee.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        verify(cupOfCoffeeService).updateCupOfCoffee(any(CupOfCoffee.class));
    }

    @Test
    void cleanMachine() throws URISyntaxException {
        Cleaner cleaner = new Cleaner();

        when(cleanerService.cleanMachine()).thenReturn(cleaner);

        RequestEntity <?> requestEntity = new RequestEntity<>(HttpMethod.PUT, new URI("/coffee_machine/clean_machine"));
        ResponseEntity<Cleaner> responseEntity = testRestTemplate.exchange(requestEntity, Cleaner.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(cleanerService).cleanMachine();
    }

    @Test
    void getAllCoffee() throws URISyntaxException {
        List <CupOfCoffee> cupOfCoffeeList = new ArrayList<>();
        cupOfCoffeeList.add(new CupOfCoffee());

        when(cupOfCoffeeService.getAllCoffee()).thenReturn(cupOfCoffeeList);

        RequestEntity <?> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI("/coffee_machine/all_coffee"));
        ResponseEntity<List> responseEntity = testRestTemplate.exchange(requestEntity, List.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        verify(cupOfCoffeeService).getAllCoffee();
    }
}