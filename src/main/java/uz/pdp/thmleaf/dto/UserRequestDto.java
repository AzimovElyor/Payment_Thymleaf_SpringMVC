package uz.pdp.thmleaf.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.thmleaf.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
  private String username;
  private String name;
  private Integer age;
  private UserRole userRole = UserRole.USER;
  private String password;
}
