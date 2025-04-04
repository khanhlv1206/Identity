package com.example.identityservice.dto.response;

import com.example.identityservice.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String name;
    String description;
    private List<PermissionDto> permissions;


    /**
     * DTO for {@link Permission}
     */
    public static class PermissionDto implements Serializable {
        private String name;
        private String description;
        Set<Permission> permissions;
    }
}
