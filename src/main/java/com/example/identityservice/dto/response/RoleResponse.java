package com.example.identityservice.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String name;
    String description;
    private List<PermissionDto> permissions;

    public static class PermissionDto implements Serializable {
        private String name;
        private String description;
    }
}
