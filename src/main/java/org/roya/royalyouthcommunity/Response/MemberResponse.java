package org.roya.royalyouthcommunity.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    private String memberId;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;


}
