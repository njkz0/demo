package com.coffee.demo.service.impl;

import com.coffee.demo.dao.CleanerDAO;
import com.coffee.demo.model.Cleaner;
import com.coffee.demo.service.CleanerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CleanerServiceImpl implements CleanerService {

    private final CleanerDAO cleanerDAO;

    public Cleaner plusPreparation(){
        Cleaner cleaner = cleanerDAO.getLastCountOfPreparations();
        if(cleaner.getCountOfPreparations() > 3){
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
