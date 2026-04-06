package app.ports;

import java.math.BigDecimal;
import java.util.List;

import app.domain.models.Loan;
import app.domain.models.LoanStatus;

/**
 * Puerto (Interfaz) para servicios de negocio de Loan
 * Define operaciones del dominio de préstamos
 */
public interface LoanService {
    
    /**
     * Crea una nueva solicitud de préstamo
     */
    Loan createLoanRequest(String applicantClientId, BigDecimal requestedAmount, Integer termMonths) throws Exception;
    
    /**
     * Aprueba una solicitud de préstamo
     */
    void approveLoan(Long loanId, BigDecimal approvedAmount) throws Exception;
    
    /**
     * Desembolsa un préstamo aprobado a una cuenta
     */
    void disburseLoan(Long loanId, String disbursementAccount) throws Exception;
    
    /**
     * Obtiene un préstamo por ID
     */
    Loan getLoan(Long loanId) throws Exception;
    
    /**
     * Obtiene todos los préstamos de un cliente
     */
    List<Loan> getLoansByClient(String clientId);
    
    /**
     * Obtiene préstamos por estado
     */
    List<Loan> getLoansByStatus(LoanStatus status);
}
