package com.team3.task8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "fund")
public class Fund {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String symbol;

    private double price;

    public Fund(String name,
                String symbol,
                double price) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
    }
}