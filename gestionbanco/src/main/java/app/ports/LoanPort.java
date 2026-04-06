package app.ports;

import java.util.List;
import java.util.Optional;

import app.domain.models.Loan;
import app.domain.models.LoanStatus;

/**
 * Puerto (Interfaz) para la persistencia de Loan
 * Parte del Agregado de Loan
 */
public interface LoanPort {
    
    /**
     * Guarda un nuevo préstamo
     */
    void save(Loan loan);
    
    /**
     * Busca un préstamo por ID
     */
    Optional<Loan> findById(Long loanId);
    
    /**
     * Busca préstamos por cliente solicitante
     */
    List<Loan> findByApplicantClientId(String applicantClientId);
    
    /**
     * Busca préstamos por estado
     */
    List<Loan> findByStatus(LoanStatus status);
    
    /**
     * Actualiza un préstamo existente
     */
    void update(Loan loan);
    
    /**
     * Elimina un préstamo
     */
    void delete(Long loanId);
    
    /**
     * Busca todos los préstamos
     */
    List<Loan> findAll();
}
