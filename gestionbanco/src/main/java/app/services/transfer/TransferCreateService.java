package app.services.transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import app.domain.models.Transfer;
import app.domain.models.TransferStatus;
import app.ports.BankAccountService;
import app.ports.TransferPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferCreateService {
    private final TransferPort transferRepository;
    private final BankAccountService bankAccountService;

    public Transfer createTransfer(String originAccount, String destinationAccount,
                                   BigDecimal amount, Long creatorUserId) throws Exception {
        if (!isValidTransfer(originAccount, destinationAccount, amount)) {
            throw new Exception("Transferencia inválida");
        }

        bankAccountService.getAccount(originAccount);
        bankAccountService.getAccount(destinationAccount);

        Transfer transfer = new Transfer();
        transfer.setTransferId(generateTransferId());
        transfer.setOriginAccount(originAccount);
        transfer.setDestinationAccount(destinationAccount);
        transfer.setAmount(amount);
        transfer.setCreationDate(LocalDateTime.now());
        transfer.setCreatorUserId(creatorUserId);
        transfer.setTransferStatus(TransferStatus.PENDING);

        transferRepository.save(transfer);
        return transfer;
    }

    private boolean isValidTransfer(String originAccount, String destinationAccount, BigDecimal amount) {
        if (originAccount == null || originAccount.isEmpty()) {
            return false;
        }
        if (destinationAccount == null || destinationAccount.isEmpty()) {
            return false;
        }
        if (originAccount.equals(destinationAccount)) {
            return false;
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        try {
            BigDecimal originBalance = bankAccountService.getBalance(originAccount);
            return originBalance.compareTo(amount) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    private Long generateTransferId() {
        return System.currentTimeMillis();
    }
}
