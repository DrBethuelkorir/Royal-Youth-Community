package org.roya.royalyouthcommunity.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse extends UserResponse{
    private String token;
    private Collection<GrantedAuthority> role;
    private String expirationTime;
    private String message;
}