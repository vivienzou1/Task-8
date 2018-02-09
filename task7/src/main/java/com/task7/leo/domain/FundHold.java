package com.task7.leo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "fund_hold")
public class FundHold {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "fund_id")
    private Fund fund;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private double share;

    public FundHold(Fund fund, Customer customer, double share) {
        this.fund = fund;
        this.customer = customer;
        this.share = share;
    }

    @Override
    public String toString() {
        return "hahaha";
    }

}
