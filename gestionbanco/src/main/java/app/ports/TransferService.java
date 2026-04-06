package app.ports;

import java.math.BigDecimal;
import java.util.List;

import app.domain.models.Transfer;

/**
 * Puerto (Interfaz) para servicios de negocio de Transfer
 * Define operaciones del dominio de transferencias
 */
public interface TransferService {
    
    /**
     * Crea una nueva transferencia entre dos cuentas
     */
    Transfer createTransfer(String originAccount, String destinationAccount, 
                           BigDecimal amount, Long creatorUserId) throws Exception;
    
    /**
     * Aprueba una transferencia pendiente
     */
    void approveTransfer(Long transferId, Long approverUserId) throws Exception;
    
    /**
     * Obtiene una transferencia por ID
     */
    Transfer getTransfer(Long transferId) throws Exception;
    
    /**
     * Obtiene todas las transferencias de una cuenta origen
     */
    List<Transfer> getTransfersByOriginAccount(String accountNumber);
    
    /**
     * Obtiene todas las transferencias a una cuenta destino
     */
    List<Transfer> getTransfersByDestinationAccount(String accountNumber);
    
    /**
     * Obtiene todas las transferencias pendientes
     */
    List<Transfer> getPendingTransfers();
}
