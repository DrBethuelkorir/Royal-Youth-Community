package org.roya.royalyouthcommunity.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    private Long id;
    private double amount;
    private String currency;
    private String status;
    private String provider;
    private String providerRef;
    private String idempotencyKey;
    private LocalDateTime createdAt;
    private UserDto userDto;
    private PaymentDto paymentDto;
}

