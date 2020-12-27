package com.coffee.demo.service;

import com.coffee.demo.dao.CleanerDAO;
import com.coffee.demo.model.Cleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CleanerServiceTest {

    @Autowired
    CleanerService cleanerService;

    @MockBean
    CleanerDAO cleanerDAO;

    Cleaner cleaner;

    @BeforeEach
    void setUp(){
        cleaner = new Cleaner();
        cleaner.setCountOfPreparations(2);

    }

    @Test
    void plusPreparation() {
        Cleaner returnCleaner = new Cleaner();
        returnCleaner.setCountOfPreparations(3);

        when(cleanerDAO.getLastCountOfPreparations()).thenReturn(cleaner);
        when(cleanerDAO.save(any(Cleaner.class))).thenReturn(returnCleaner);

        Cleaner result = cleanerService.plusPreparation();

        assertEquals(result.getCountOfPreparations(), returnCleaner.getCountOfPreparations());
        verify(cleanerDAO).getLastCountOfPreparations();
        verify(cleanerDAO).save(any(Cleaner.class));
    }

    @Test
    void cleanMachine() {
        Cleaner returnCleaner= new Cleaner();
        returnCleaner.setCountOfPreparations(0);

        when(cleanerDAO.getLastCountOfPreparations()).thenReturn(cleaner);
        when(cleanerDAO.save(any(Cleaner.class))).thenReturn(returnCleaner);

        Cleaner result = cleanerService.cleanMachine();

        assertEquals(result.getCountOfPreparations(), returnCleaner.getCountOfPreparations());
        verify(cleanerDAO).getLastCountOfPreparations();
        verify(cleanerDAO).save(any(Cleaner.class));
    }
}