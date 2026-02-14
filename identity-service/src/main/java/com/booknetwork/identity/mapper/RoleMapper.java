package com.booknetwork.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.booknetwork.identity.dto.request.RoleRequest;
import com.booknetwork.identity.dto.response.RoleResponse;
import com.booknetwork.identity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
