package org.roya.royalyouthcommunity.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.roya.royalyouthcommunity.Dtos.UserDto;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int statusCode;
    private String message;
    private UserDto userDto;
    private List<UserDto> usersDto;
}
