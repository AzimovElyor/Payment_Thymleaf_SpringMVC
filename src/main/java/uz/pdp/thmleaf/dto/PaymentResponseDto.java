package uz.pdp.thmleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentResponseDto {
    private UUID id;
    private String ownerName;
    private String senderCardNumber;
    private String serviceName;
    private BigDecimal amount;
    private String fields;
    private BigDecimal commission;
    private LocalDateTime createdDate;



}
