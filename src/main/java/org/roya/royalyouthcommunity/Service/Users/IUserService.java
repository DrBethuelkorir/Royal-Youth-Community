package org.roya.royalyouthcommunity.Service.Users;

import org.roya.royalyouthcommunity.Dtos.LoginDto;
import org.roya.royalyouthcommunity.Dtos.UserDto;
import org.roya.royalyouthcommunity.Response.LoginResponse;
import org.roya.royalyouthcommunity.Response.UserResponse;

public interface IUserService {
    LoginResponse login(LoginDto loginDto);
    UserResponse register(UserDto userDto);
    UserResponse updateUser(UserDto userDto);
    UserResponse deleteUser(Long Id);
    UserResponse getUserById(Long Id);
    UserResponse getAllUsers();
    UserResponse getMyInfo(String email);
}
