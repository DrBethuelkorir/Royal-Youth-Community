package org.roya.royalyouthcommunity.Utils;

import org.roya.royalyouthcommunity.Dtos.MemberDto;
import org.roya.royalyouthcommunity.Dtos.RolesDto;
import org.roya.royalyouthcommunity.Dtos.UserDto;
import org.roya.royalyouthcommunity.models.Members;
import org.roya.royalyouthcommunity.models.Roles;
import org.roya.royalyouthcommunity.models.Users;

import java.util.List;

public class UserDtoMapping {

    public static UserDto mapUserToUserDto(Users user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.getStatus());
        userDto.setEmailVerified(user.isEmailVerified());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }
    public static UserDto mapUserToUserDtoPlusMembers(Users user){
        UserDto userDto = UserDtoMapping.mapUserToUserDto(user);
        if(user.getMembers() != null && !user.getMembers().isEmpty()){
            List<MemberDto> memberDto = user.getMembers().stream()
                    .map(UserDtoMapping::mapUserToMemberDto)
                    .toList();
            userDto.setMemberDtos(memberDto);
        }
    return userDto;
    }
    public static MemberDto mapUserToMemberDto(Members member){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setFirstName(member.getFirstName());
        memberDto.setLastName(member.getLastName());
        memberDto.setActivatedAt(member.getActivatedAt());
        memberDto.setActive(member.getActive());

        return memberDto;
    }
    public static RolesDto mapRoleToRolesDto(Roles role) {
        if (role == null) return null;
        RolesDto dto = new RolesDto();
        dto.setId(role.getId());
        dto.setRole(role.getRole());
        return dto;
    }
    public static RolesDto mapRoleToRolesDtoPlusUsers(Roles role) {
        RolesDto rolesDto = UserDtoMapping.mapRoleToRolesDto(role);
        if(role.getUser() != null && !role.getUser().isEmpty()){
            List<UserDto> userDtos = role.getUser().stream()
                    .map(UserDtoMapping::mapUserToUserDto)
                    .toList();
            rolesDto.setUsers(userDtos);
        }
        return rolesDto;
    }

}

