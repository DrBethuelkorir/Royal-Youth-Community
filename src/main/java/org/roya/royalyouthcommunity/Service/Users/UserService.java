package org.roya.royalyouthcommunity.Service.Users;

import lombok.RequiredArgsConstructor;
import org.roya.royalyouthcommunity.Dtos.LoginDto;
import org.roya.royalyouthcommunity.Dtos.UserDto;
import org.roya.royalyouthcommunity.Exeption.CustomExeption;
import org.roya.royalyouthcommunity.Repositories.UserRepository;
import org.roya.royalyouthcommunity.Response.LoginResponse;
import org.roya.royalyouthcommunity.Response.UserResponse;
import org.roya.royalyouthcommunity.Utils.JwtUtils;
import org.roya.royalyouthcommunity.Utils.UserDtoMapping; // Need this for mapping
import org.roya.royalyouthcommunity.models.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public LoginResponse login(LoginDto loginDto) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            String token = jwtUtils.generateToken(authentication);
            loginResponse.setToken(token);
            loginResponse.setStatusCode(200);
            loginResponse.setMessage("success");
            loginResponse.setRole((Collection<GrantedAuthority>) authentication.getAuthorities());
            loginResponse.setExpirationTime("1day");

        } catch (AuthenticationException e) {
            // 401 is the correct status code for bad credentials, not 500
            loginResponse.setStatusCode(401);
            loginResponse.setMessage("Invalid email or password");
        } catch (Exception e) {
            loginResponse.setStatusCode(500);
            loginResponse.setMessage("ERROR OCCURRED: " + e.getMessage());
        }
        return loginResponse;
    }

    @Override
    public UserResponse register(UserDto userDto) {
        UserResponse response = new UserResponse();

        // 1. Check if the email is already registered
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            response.setStatusCode(400);
            response.setMessage("Email is already in use");
            return response;
        }

        try {
            // 2. Map DTO to Entity
            Users newUser = new Users();
            newUser.setEmail(userDto.getEmail());

            // IMPORTANT: Always encode (hash) passwords before saving!
            newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

            // Set default properties
            newUser.setStatus("ACTIVE");
            newUser.setEmailVerified(false); // Usually false until they click an email link
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

            // 3. Save to database
            Users savedUser = userRepository.save(newUser);

            // 4. Return mapped response (Use Basic mapping to avoid returning passwords)
            response.setStatusCode(201); // 201 Created
            response.setMessage("User registered successfully");
            response.setUserDto(UserDtoMapping.mapUserToUserDto(savedUser));

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error registering user: " + e.getMessage());
        }

        return response;
    }

    @Override
    public UserResponse updateUser(UserDto userDto) {
        UserResponse response = new UserResponse();

        // 1. Ensure user ID is provided
        if (userDto.getId() == null) {
            response.setStatusCode(400);
            response.setMessage("User ID is required for update");
            return response;
        }

        // 2. Find existing user
        Users existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new CustomExeption("User not found with id: " + userDto.getId()));

        try {
            // 3. Update fields if they are provided in the DTO
            if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
                existingUser.setEmail(userDto.getEmail());
            }

            // Only update password if a new one was explicitly provided
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }

            if (userDto.getStatus() != null) {
                existingUser.setStatus(userDto.getStatus());
            }

            existingUser.setUpdatedAt(LocalDateTime.now());
            Users updatedUser = userRepository.save(existingUser);

            response.setStatusCode(200);
            response.setMessage("User updated successfully");
            response.setUserDto(UserDtoMapping.mapUserToUserDto(updatedUser));

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error updating user: " + e.getMessage());
        }

        return response;
    }

    @Override
    public UserResponse deleteUser(Long userId) {
        UserResponse response = new UserResponse();

        try {
            if (!userRepository.existsById(userId)) {
                response.setStatusCode(404);
                response.setMessage("User not found");
                return response;
            }

            userRepository.deleteById(userId);
            response.setStatusCode(200);
            response.setMessage("User deleted successfully");

        } catch (NumberFormatException e) {
            response.setStatusCode(400);
            response.setMessage("Invalid User ID format");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error deleting user: " + e.getMessage());
        }

        return response;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        UserResponse response = new UserResponse();

        try {

            Users user = userRepository.findById(userId)
                    .orElseThrow(() -> new CustomExeption("User not found with id: " + userId));

            response.setStatusCode(200);
            response.setMessage("Success");
            // Use PlusMembers if you want to return their member profile as well
            response.setUserDto(UserDtoMapping.mapUserToUserDtoPlusMembers(user));

        } catch (NumberFormatException e) {
            response.setStatusCode(400);
            response.setMessage("Invalid User ID format");
        } catch (CustomExeption e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public UserResponse getAllUsers() {
        UserResponse response = new UserResponse();

        try {
            List<Users> users = userRepository.findAll();

            // Map entities to DTOs to prevent sending database objects (and passwords) to the client
            List<UserDto> userDtos = users.stream()
                    .map(UserDtoMapping::mapUserToUserDto)
                    .collect(Collectors.toList());

            response.setStatusCode(200);
            response.setMessage("Success");
            response.setUsersDto(userDtos); // Assuming UserResponse has a List<UserDto> field named userDtos

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error fetching users: " + e.getMessage());
        }

        return response;
    }

    @Override
    public UserResponse getMyInfo(String email) {
        UserResponse response = new UserResponse();

        try {
            Users user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new CustomExeption("User not found with email: " + email));

            response.setStatusCode(200);
            response.setMessage("Success");
            response.setUserDto(UserDtoMapping.mapUserToUserDtoPlusMembers(user));

        } catch (CustomExeption e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}