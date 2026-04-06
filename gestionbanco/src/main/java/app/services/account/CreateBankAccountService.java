package app.services.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import app.domain.models.AccountStatus;
import app.domain.models.AccountType;
import app.domain.models.BankAccount;
import app.domain.models.Currency;
import app.ports.BankAccountPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateBankAccountService {
    private final BankAccountPort bankAccountRepository;

    public BankAccount createAccount(String holderId, AccountType accountType, Currency currency) {
        String accountNumber = generateAccountNumber();

        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setHolderId(holderId);
        account.setAccountType(accountType);
        account.setCurrency(currency);
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setOpeningDate(LocalDate.now());

        bankAccountRepository.save(account);
        return account;
    }

    private String generateAccountNumber() {
        return "ACC" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}
