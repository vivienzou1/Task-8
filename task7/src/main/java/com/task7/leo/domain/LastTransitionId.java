package com.task7.leo.domain;

import com.task7.leo.dto.UserRegisterForm;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "last_transaction_id")
public class LastTransitionId {
    @Id
    @GeneratedValue
    private long id;

    private long lastTransitionId;

    public LastTransitionId(long lastTransitionId) {
        this.lastTransitionId = lastTransitionId;
    }
}
