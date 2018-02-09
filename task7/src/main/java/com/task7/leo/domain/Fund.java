package com.task7.leo.domain;

import com.task7.leo.dto.FundCreateForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "fund")
public class Fund {
    @Id
    @GeneratedValue
    private long id;

    private String fundName;

    private String fundSymbol;

    private double price;

    @Temporal(TemporalType.DATE)
    private Date lastTransition;

    @OneToMany
    private List<FundHold> fundHolds;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name ="priceLine")
    private List<String> priceLine;

    public Fund(FundCreateForm fundCreateForm) {
        this.fundName = fundCreateForm.getFundName();
        this.fundSymbol = fundCreateForm.getFundSymbol();
        this.price = Double.parseDouble(fundCreateForm.getPrice());
        this.fundHolds = new ArrayList<>();
        this.priceLine = new ArrayList<>();
    }

    @Override
    public String toString() {
        if (id == 0) return "";
        StringBuilder out = new StringBuilder();
        out.append(this.fundSymbol);
        for (String i : priceLine) {
            out.append(" ");
            String[] tem =i.split(" ");
            out.append(tem[1]);
            out.append("-");
            out.append(tem[2]);
            out.append("-");
            out.append(tem[tem.length - 1]);
        }
        return out.toString();
    }
}
