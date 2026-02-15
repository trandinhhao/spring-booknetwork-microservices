package com.booknetwork.identity.mapper;

import org.mapstruct.Mapper;

import com.booknetwork.identity.dto.request.PermissionRequest;
import com.booknetwork.identity.dto.response.PermissionResponse;
import com.booknetwork.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
