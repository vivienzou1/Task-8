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
@Table(name = "fundhold")
public class FundHold {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private double shares;

    private double price;

    public FundHold(String name,
                    double shares,
                    double price) {
        this.name = name;
        this.shares = shares;
        this.price = price;
    }
}