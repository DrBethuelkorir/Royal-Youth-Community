package org.roya.royalyouthcommunity.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    private String email;
    private String status;
    private boolean emailVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<PaymentDto> paymentDto;
    private List<MemberDto> memberDtos;
    private List<RolesDto> rolesDtos;
}
