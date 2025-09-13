package com.svick.brz.spring.utils;

import com.svick.brz.spring.model.dto.RoleDto;
import com.svick.brz.spring.model.dto.UserDto;
import com.svick.brz.spring.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDTO(UserEntity user) {
        List<RoleDto> roles;
        if (user.getRole() != null) {
            roles = Collections.singletonList(new RoleDto(user.getRole().getId(), user.getRole().getRoleName()));
        } else {
            roles = Collections.emptyList();
        }
        return new UserDto(user.getId(), user.getName(), user.getEmail(), roles, user.getStatus());
    }

    public Page<UserDto> toDTOPage(Page<UserEntity> usersPage) {
        List<UserDto> dtoList = usersPage.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, usersPage.getPageable(), usersPage.getTotalElements());
    }
}


