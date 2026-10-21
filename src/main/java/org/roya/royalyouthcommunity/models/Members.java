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
@Table(name = "roles")
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime activatedAt;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;
    @OneToOne()
    @JoinColumn(name = "payment_id", unique = true)
    private Payment payment;
}
