package app.services.account;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import app.domain.models.AccountStatus;
import app.domain.models.BankAccount;
import app.ports.BankAccountPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WithdrawBankAccountService {
    private final BankAccountPort bankAccountRepository;

    public void withdraw(String accountNumber, BigDecimal amount) throws Exception {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));

        if (!account.getAccountStatus().equals(AccountStatus.ACTIVE)) {
            throw new Exception("La cuenta no está activa");
        }

        if (account.getCurrentBalance().compareTo(amount) < 0) {
            throw new Exception("Fondos insuficientes");
        }

        account.setCurrentBalance(account.getCurrentBalance().subtract(amount));
        bankAccountRepository.update(account);
    }
}
