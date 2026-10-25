package org.roya.royalyouthcommunity.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.roya.royalyouthcommunity.Dtos.MemberDto;
import org.roya.royalyouthcommunity.Dtos.UserDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    private int statusCode;
    private String message;
    private MemberDto memberDto;
    private List<MemberDto> membersDto;
}
