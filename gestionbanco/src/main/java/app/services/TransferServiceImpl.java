package app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import app.domain.models.Transfer;
import app.ports.TransferService;
import app.services.transfer.TransferApprovalService;
import app.services.transfer.TransferCreateService;
import app.services.transfer.TransferQueryService;
import lombok.RequiredArgsConstructor;

/**
 * Implementación del servicio de dominio para Transfer
 * Delegación de cada función a servicios independientes.
 */
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferCreateService transferCreateService;
    private final TransferApprovalService transferApprovalService;
    private final TransferQueryService transferQueryService;

    @Override
    public Transfer createTransfer(String originAccount, String destinationAccount, BigDecimal amount, Long creatorUserId) throws Exception {
        return transferCreateService.createTransfer(originAccount, destinationAccount, amount, creatorUserId);
    }

    @Override
    public void approveTransfer(Long transferId, Long approverUserId) throws Exception {
        transferApprovalService.approveTransfer(transferId, approverUserId);
    }

    @Override
    public Transfer getTransfer(Long transferId) throws Exception {
        return transferQueryService.getTransfer(transferId);
    }

    @Override
    public List<Transfer> getTransfersByOriginAccount(String accountNumber) {
        return transferQueryService.getTransfersByOriginAccount(accountNumber);
    }

    @Override
    public List<Transfer> getTransfersByDestinationAccount(String accountNumber) {
        return transferQueryService.getTransfersByDestinationAccount(accountNumber);
    }

    @Override
    public List<Transfer> getPendingTransfers() {
        return transferQueryService.getPendingTransfers();
    }
}
