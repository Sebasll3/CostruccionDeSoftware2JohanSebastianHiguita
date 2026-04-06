package app.ports;

import java.util.List;
import java.util.Optional;

import app.domain.models.AccountStatus;
import app.domain.models.AccountType;
import app.domain.models.BankAccount;

/**
 * Puerto (Interfaz) para la persistencia de BankAccount
 * Parte del Agregado de BankAccount
 */
public interface BankAccountPort {
    
    /**
     * Guarda una nueva cuenta bancaria
     */
    void save(BankAccount bankAccount);
    
    /**
     * Busca una cuenta por su número de cuenta
     */
    Optional<BankAccount> findByAccountNumber(String accountNumber);
    
    /**
     * Busca una cuenta por ID
     */
    Optional<BankAccount> findById(String accountId);
    
    /**
     * Busca todas las cuentas de un titular
     */
    List<BankAccount> findByHolderId(String holderId);
    
    /**
     * Busca cuentas por tipo
     */
    List<BankAccount> findByAccountType(AccountType accountType);
    
    /**
     * Busca cuentas por estado
     */
    List<BankAccount> findByStatus(AccountStatus status);
    
    /**
     * Actualiza una cuenta existente
     */
    void update(BankAccount bankAccount);
    
    /**
     * Elimina una cuenta
     */
    void delete(String accountId);
    
    /**
     * Busca todas las cuentas
     */
    List<BankAccount> findAll();
}
