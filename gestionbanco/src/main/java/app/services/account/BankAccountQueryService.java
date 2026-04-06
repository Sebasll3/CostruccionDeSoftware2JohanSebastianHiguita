package app.services.account;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.BankAccount;
import app.ports.BankAccountPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAccountQueryService {
    private final BankAccountPort bankAccountRepository;

    public BankAccount getAccount(String accountNumber) throws Exception {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new Exception("Cuenta no encontrada"));
    }

    public List<BankAccount> getAccountsByHolder(String holderId) {
        return bankAccountRepository.findByHolderId(holderId);
    }

    public boolean accountExists(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber).isPresent();
    }
}
