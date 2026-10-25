package org.roya.royalyouthcommunity.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.roya.royalyouthcommunity.Dtos.RolesDto;
import org.roya.royalyouthcommunity.models.Roles;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolesResponse {
 private int statusCode;
 private String message;
 private RolesDto roleDto;
 private List<RolesDto> rolesDto;
}
