package app.services.transfer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import app.domain.models.Transfer;
import app.domain.models.TransferStatus;
import app.ports.BankAccountService;
import app.ports.TransferPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferApprovalService {
    private final TransferPort transferRepository;
    private final BankAccountService bankAccountService;

    public void approveTransfer(Long transferId, Long approverUserId) throws Exception {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new Exception("Transferencia no encontrada"));

        if (!transfer.getTransferStatus().equals(TransferStatus.PENDING)) {
            throw new Exception("La transferencia no está pendiente");
        }

        try {
            bankAccountService.withdraw(transfer.getOriginAccount(), transfer.getAmount());
            bankAccountService.deposit(transfer.getDestinationAccount(), transfer.getAmount());

            transfer.setTransferStatus(TransferStatus.EXECUTED);
            transfer.setApprovalDate(LocalDateTime.now());
            transfer.setApproverUserId(approverUserId);
        } catch (Exception e) {
            transfer.setTransferStatus(TransferStatus.FAILED);
            transferRepository.update(transfer);
            throw new Exception("Error al procesar la transferencia: " + e.getMessage());
        }

        transferRepository.update(transfer);
    }
}
