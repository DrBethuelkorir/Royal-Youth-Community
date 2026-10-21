package org.roya.royalyouthcommunity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payments")
public class payment {
    private Long id;
    private double amount;
    private String currency;
    private String status;
    private String provider;
    private String providerRef;
    private String idempotencyKey;
    private LocalDateTime createdAt;
}
