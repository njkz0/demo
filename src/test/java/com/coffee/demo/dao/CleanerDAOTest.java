package com.coffee.demo.dao;

import com.coffee.demo.model.Cleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CleanerDAOTest {

    @Autowired
    CleanerDAO cleanerDAO;

    Cleaner cleaner;

    @BeforeEach
    void setUp(){
        cleaner= new Cleaner();
        cleaner.setCountOfPreparations(5);
    }

    @Test
    void getLastCountOfPreparations() {
       Cleaner savedCleaner = cleanerDAO.save(cleaner);
       Cleaner found = cleanerDAO.getLastCountOfPreparations();
       assertEquals(savedCleaner.getCountOfPreparations(), found.getCountOfPreparations());


    }
}