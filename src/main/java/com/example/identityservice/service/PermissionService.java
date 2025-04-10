package com.example.identityservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.identityservice.dto.request.PermissionRequest;
import com.example.identityservice.dto.response.PermissionResponse;
import com.example.identityservice.entity.Permission;
import com.example.identityservice.mapper.PermissionMapper;
import com.example.identityservice.repository.PermissionRepository;

@Service
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}
