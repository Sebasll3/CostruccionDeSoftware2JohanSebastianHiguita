package app.ports;

import java.util.List;
import java.util.Optional;

import app.domain.models.Transfer;
import app.domain.models.TransferStatus;

/**
 * Puerto (Interfaz) para la persistencia de Transfer
 * Parte del Agregado de Transfer
 */
public interface TransferPort {
    
    /**
     * Guarda una nueva transferencia
     */
    void save(Transfer transfer);
    
    /**
     * Busca una transferencia por ID
     */
    Optional<Transfer> findById(Long transferId);
    
    /**
     * Busca transferencias desde una cuenta origen
     */
    List<Transfer> findByOriginAccount(String originAccount);
    
    /**
     * Busca transferencias hacia una cuenta destino
     */
    List<Transfer> findByDestinationAccount(String destinationAccount);
    
    /**
     * Busca transferencias por estado
     */
    List<Transfer> findByStatus(TransferStatus transferStatus);
    
    /**
     * Actualiza una transferencia existente
     */
    void update(Transfer transfer);
    
    /**
     * Elimina una transferencia
     */
    void delete(Long transferId);
    
    /**
     * Busca todas las transferencias
     */
    List<Transfer> findAll();
}
