package app.ports;

import java.util.List;

import app.domain.models.AuditRecord;
import app.domain.models.OperationType;

/**
 * Puerto (Interfaz) para persistencia de AuditRecord
 * Permite auditar todas las operaciones importantes
 */
public interface AuditPort {
    
    /**
     * Guarda un registro de auditoría
     */
    void save(AuditRecord auditRecord);
    
    /**
     * Busca registros de auditoría por usuario
     */
    List<AuditRecord> findByUserId(int userId);
    
    /**
     * Busca registros de auditoría por tipo de operación
     */
    List<AuditRecord> findByOperationType(OperationType operationType);
    
    /**
     * Busca registros de auditoría por producto afectado
     */
    List<AuditRecord> findByProductId(String productId);
    
    /**
     * Obtiene todos los registros de auditoría
     */
    List<AuditRecord> findAll();
}
