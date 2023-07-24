package com.example.test.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "value_table")
@Data
public class ValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "counted_value")
    private int countedValue;

}
