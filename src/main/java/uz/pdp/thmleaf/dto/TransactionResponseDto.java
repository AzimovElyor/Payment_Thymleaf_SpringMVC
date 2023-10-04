package uz.pdp.thmleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.thmleaf.enums.TransactionRole;
/*import uz.pdp.springboot.entity.Transaction;
import uz.pdp.springboot.enums.TransactionRole;*/

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponseDto {
 private String ownerName;
 private String senderCardNumber;
 private String receiverCardNumber;
 private BigDecimal price;
 private BigDecimal commission;
 private TransactionRole role;

 private LocalDateTime createdDate;

 /*public TransactionResponseDto(Transaction transaction, TransactionRole role){
  this.ownerName = transaction.getOwner().getName();
  this.senderCardNumber = transaction.getSenderCard().getCardNumber();
  this.receiverCardNumber = transaction.getReceiverCard().getCardNumber();
  this.price = transaction.getBalance();
  this.role = role;
  this.createdDate = transaction.getCreatedDate();
 }*/
}
