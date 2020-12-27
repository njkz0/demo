package com.coffee.demo.dao;

import com.coffee.demo.model.CupOfCoffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupOfCoffeeDAO extends JpaRepository <CupOfCoffee, Integer> {

    CupOfCoffee findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM coffee_type")
    List <CupOfCoffee> findAllExistedCoffee();


}
