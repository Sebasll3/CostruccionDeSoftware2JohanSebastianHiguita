package domain.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
//Transferencia
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
 
public class Transfer {
    private Long transferId;
    private String originAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private LocalDateTime creationDate;
    private LocalDateTime approvalDate;
    private String transferStatus;
    private Long creatorUserId;
    private Long approverUserId;
}
