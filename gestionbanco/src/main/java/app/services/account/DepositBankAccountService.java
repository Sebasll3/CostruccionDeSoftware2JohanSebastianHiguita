package app.services.account;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import app.domain.models.AccountStatus;
import app.domain.models.BankAccount;
import app.ports.BankAccountPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepositBankAccountService {
    private final BankAccountPort bankAccountRepository;

    public void deposit(String accountNumber, BigDecimal amount) throws Exception {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));

        if (!account.getAccountStatus().equals(AccountStatus.ACTIVE)) {
            throw new Exception("La cuenta no está activa");
        }

        account.setCurrentBalance(account.getCurrentBalance().add(amount));
        bankAccountRepository.update(account);
    }
}
