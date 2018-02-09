package com.task7.leo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "fund_id")
    private Fund fund;

    private double amount;

    private double share;

    private double price;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String type;

    public Transaction(Customer customer, Fund fund, double amount, double share, double price, String type) {
        this.customer = customer;
        this.fund = fund;
        this.amount = amount;
        this.share = share;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        String out = "Customer Username is " + customer.getUsername() + "\n";
        if(fund != null)out += "Fund Name is " + fund.getFundName() + ", Fund Symbol is " + fund.getFundSymbol() + "\n";
        out += "Amount is " + amount + "\n";
        out += "Share Number is" + share + "\n";
        out += "Type is " + type + "  Date: " + date;
        return out;
    }

}
