package app.ports;

import java.math.BigDecimal;
import java.util.List;

import app.domain.models.AccountType;
import app.domain.models.BankAccount;
import app.domain.models.Currency;

/**
 * Puerto (Interfaz) para servicios de negocio de BankAccount
 * Define operaciones del dominio
 */
public interface BankAccountService {
    
    /**
     * Crea una nueva cuenta bancaria
     */
    BankAccount createAccount(String holderId, AccountType accountType, Currency currency);
    
    /**
     * Deposita dinero en una cuenta
     */
    void deposit(String accountNumber, BigDecimal amount) throws Exception;
    
    /**
     * Retira dinero de una cuenta
     */
    void withdraw(String accountNumber, BigDecimal amount) throws Exception;
    
    /**
     * Obtiene el balance de una cuenta
     */
    BigDecimal getBalance(String accountNumber) throws Exception;
    
    /**
     * Obtiene una cuenta por número
     */
    BankAccount getAccount(String accountNumber) throws Exception;
    
    /**
     * Obtiene todas las cuentas de un titular
     */
    List<BankAccount> getAccountsByHolder(String holderId);
}
