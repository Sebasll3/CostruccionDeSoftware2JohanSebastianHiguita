package domain.models;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditRecord {
    private int idAuditRecord;
    private String OperationType;
    private LocalDateTime OperationDateTime;
    private int userId;
    private String RolUser;
    private String AffectedProductId;
    private Map<String, Object> DetailData; 

}
