package app.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import app.domain.models.AuditRecord;
import app.domain.models.OperationType;
import app.domain.models.SystemRol;
import app.ports.AuditPort;
import lombok.RequiredArgsConstructor;

/**
 * Servicio de auditoría para registrar todas las operaciones importantes
 */
@Service
@RequiredArgsConstructor
public class AuditServiceImpl {
    
    private final AuditPort auditRepository;
    
    public void logDeposit(String accountNumber, String amount, int userId, SystemRol userRole, String productId) {
        Map<String, Object> details = new HashMap<>();
        details.put("accountNumber", accountNumber);
        details.put("amount", amount);
        details.put("type", OperationType.ACCOUNT_OPENING.name());
        
        AuditRecord record = new AuditRecord();
        record.setOperationType(OperationType.ACCOUNT_OPENING);
        record.setOperationDateTime(LocalDateTime.now());
        record.setUserId(userId);
        record.setUserRole(userRole);
        record.setAffectedProductId(productId);
        record.setDetailData(details);
        
        auditRepository.save(record);
    }
    
    public void logWithdraw(String accountNumber, String amount, int userId, SystemRol userRole, String productId) {
        Map<String, Object> details = new HashMap<>();
        details.put("accountNumber", accountNumber);
        details.put("amount", amount);
        details.put("type", OperationType.TRANSFER_REJECTION.name());
        
        AuditRecord record = new AuditRecord();
        record.setOperationType(OperationType.TRANSFER_REJECTION);
        record.setOperationDateTime(LocalDateTime.now());
        record.setUserId(userId);
        record.setUserRole(userRole);
        record.setAffectedProductId(productId);
        record.setDetailData(details);
        
        auditRepository.save(record);
    }
    
    public void logTransfer(String originAccount, String destinationAccount, String amount, 
                           int userId, SystemRol userRole, String transferId) {
        Map<String, Object> details = new HashMap<>();
        details.put("originAccount", originAccount);
        details.put("destinationAccount", destinationAccount);
        details.put("amount", amount);
        details.put("type", OperationType.TRANSFER_CREATION.name());
        
        AuditRecord record = new AuditRecord();
        record.setOperationType(OperationType.TRANSFER_CREATION);
        record.setOperationDateTime(LocalDateTime.now());
        record.setUserId(userId);
        record.setUserRole(userRole);
        record.setAffectedProductId(transferId);
        record.setDetailData(details);
        
        auditRepository.save(record);
    }
    
    public void logLoanRequest(String clientId, String amount, int userId, SystemRol userRole, String loanId) {
        Map<String, Object> details = new HashMap<>();
        details.put("clientId", clientId);
        details.put("requestedAmount", amount);
        details.put("type", OperationType.LOAN_REQUEST.name());
        
        AuditRecord record = new AuditRecord();
        record.setOperationType(OperationType.LOAN_REQUEST);
        record.setOperationDateTime(LocalDateTime.now());
        record.setUserId(userId);
        record.setUserRole(userRole);
        record.setAffectedProductId(loanId);
        record.setDetailData(details);
        
        auditRepository.save(record);
    }
    
    public void logUserOperation(OperationType operation, int userId, SystemRol userRole, String details) {
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("operation", operation.name());
        detailMap.put("details", details);
        
        AuditRecord record = new AuditRecord();
        record.setOperationType(operation);
        record.setOperationDateTime(LocalDateTime.now());
        record.setUserId(userId);
        record.setUserRole(userRole);
        record.setAffectedProductId(String.valueOf(userId));
        record.setDetailData(detailMap);
        
        auditRepository.save(record);
    }
    
    public List<AuditRecord> getAuditsByUser(int userId) {
        return auditRepository.findByUserId(userId);
    }
    
    public List<AuditRecord> getAuditsByOperation(OperationType operationType) {
        return auditRepository.findByOperationType(operationType);
    }
}
