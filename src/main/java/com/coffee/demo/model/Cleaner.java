package com.coffee.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cleaner")
public class Cleaner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "count")
    private Integer countOfPreparations;
}
