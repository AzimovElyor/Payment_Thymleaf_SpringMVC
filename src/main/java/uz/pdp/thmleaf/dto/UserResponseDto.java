package uz.pdp.thmleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.thmleaf.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String username;
    private String name;
    private Integer age;
    private UserRole userRole;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
