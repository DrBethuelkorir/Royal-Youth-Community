package org.roya.royalyouthcommunity.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private String currency;
    private String status;
    private String provider;
    private String providerRef;
    private String idempotencyKey;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private  Members member;
}
