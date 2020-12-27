package com.coffee.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "coffee_type")
public class CupOfCoffee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
    @Column(name = "shugar_teaspoons")
    private Integer numberOfShugarTeaspoons;
    @Column(name = "coffee_teaspoons")
    private Integer numberOfCoffeeTeaspoons;
    @Column(name = "water_ml")
    private Integer mlOfWater;
    @Column(name = "milk_ml")
    private Integer mlOfMilk;
}
