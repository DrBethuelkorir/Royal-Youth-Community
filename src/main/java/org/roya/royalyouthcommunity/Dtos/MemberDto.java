    package org.roya.royalyouthcommunity.Dtos;
    
    import com.fasterxml.jackson.annotation.JsonInclude;
    import lombok.Data;
    
    import java.time.LocalDateTime;
    
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class MemberDto {
        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private LocalDateTime activatedAt;
        private Boolean active;
        private UserDto userDto;
        private PaymentDto paymentDto;
    }
