package com.example.demo.service;

import com.example.demo.dao.CleanerDAO;
import com.example.demo.model.Cleaner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CleanerService {

    private final CleanerDAO cleanerDAO;

    public Cleaner plusPreparation(){
        Cleaner cleaner = cleanerDAO.getLastCountOfPreparations();
        if(cleaner.getCountOfPreparations() >= 3){
            throw new RuntimeException ("Need cleaning");
        }
       cleaner.setCountOfPreparations(cleaner.getCountOfPreparations() + 1);
       return cleanerDAO.save(cleaner);
    }

    public Cleaner cleanMachine(){
        Cleaner cleaner = cleanerDAO.getLastCountOfPreparations();
        cleaner.setCountOfPreparations(0);
        return cleanerDAO.save(cleaner);
    }
}
