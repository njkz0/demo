package com.coffee.demo.dao;

import com.coffee.demo.model.Cleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CleanerDAO extends JpaRepository <Cleaner, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM cleaner WHERE id = (SELECT max(id) FROM cleaner)")
    Cleaner getLastCountOfPreparations();
}
