package app.domain.models;

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
    private OperationType operationType;
    private LocalDateTime operationDateTime;
    private int userId;
    private SystemRol userRole;
    private String affectedProductId;
    private Map<String, Object> detailData;
}
