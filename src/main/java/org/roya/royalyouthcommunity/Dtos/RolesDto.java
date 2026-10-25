package org.roya.royalyouthcommunity.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.roya.royalyouthcommunity.models.Users;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolesDto {
    private Long id;
    private String role;
    private List<Users> users;
}
