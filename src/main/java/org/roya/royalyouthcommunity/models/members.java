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
@Table(name = "roles")
public class members {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime activatedAt;
    private Boolean active;

}
