package app.services.transfer;

import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.Transfer;
import app.domain.models.TransferStatus;
import app.ports.TransferPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferQueryService {
    private final TransferPort transferRepository;

    public Transfer getTransfer(Long transferId) throws Exception {
        return transferRepository.findById(transferId)
                .orElseThrow(() -> new Exception("Transferencia no encontrada"));
    }

    public List<Transfer> getTransfersByOriginAccount(String accountNumber) {
        return transferRepository.findByOriginAccount(accountNumber);
    }

    public List<Transfer> getTransfersByDestinationAccount(String accountNumber) {
        return transferRepository.findByDestinationAccount(accountNumber);
    }

    public List<Transfer> getPendingTransfers() {
        return transferRepository.findByStatus(TransferStatus.PENDING);
    }
}
