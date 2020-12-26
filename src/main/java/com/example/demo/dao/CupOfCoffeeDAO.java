package com.example.demo.dao;

import com.example.demo.model.CupOfCoffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupOfCoffeeDAO extends JpaRepository <CupOfCoffee, Integer> {

    CupOfCoffee findByName(String name);

    @Query(nativeQuery = true, value = "SELECT names FROM coffee_type")
    List <String> findAllExistedCoffee();


}
