package uz.pdp.thmleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceResponseDto {
    private UUID id;
    private String title;
  /*  private Category category;*/
    private LocalDateTime createdDate;

}
