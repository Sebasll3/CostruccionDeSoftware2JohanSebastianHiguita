package app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.AccountType;
import app.domain.models.BankAccount;
import app.domain.models.Currency;
import app.ports.BankAccountService;
import app.services.account.BankAccountQueryService;
import app.services.account.CreateBankAccountService;
import app.services.account.DepositBankAccountService;
import app.services.account.WithdrawBankAccountService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de dominio para BankAccount
 * Delegación de cada funcionalidad a servicios independientes.
 */
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final CreateBankAccountService createBankAccountService;
    private final DepositBankAccountService depositBankAccountService;
    private final WithdrawBankAccountService withdrawBankAccountService;
    private final BankAccountQueryService bankAccountQueryService;

    @Override
    public BankAccount createAccount(String holderId, AccountType accountType, Currency currency) {
        return createBankAccountService.createAccount(holderId, accountType, currency);
    }

    @Override
    public void deposit(String accountNumber, BigDecimal amount) throws Exception {
        depositBankAccountService.deposit(accountNumber, amount);
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) throws Exception {
        withdrawBankAccountService.withdraw(accountNumber, amount);
    }

    @Override
    public BigDecimal getBalance(String accountNumber) throws Exception {
        return bankAccountQueryService.getAccount(accountNumber).getCurrentBalance();
    }

    @Override
    public BankAccount getAccount(String accountNumber) throws Exception {
        return bankAccountQueryService.getAccount(accountNumber);
    }

    @Override
    public List<BankAccount> getAccountsByHolder(String holderId) {
        return bankAccountQueryService.getAccountsByHolder(holderId);
    }
}

